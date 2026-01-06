#!/usr/bin/env bash
set -e

# Ensure jenkins can access the Docker socket
if [ -S /var/run/docker.sock ]; then
  SOCK_GID="$(stat -c '%g' /var/run/docker.sock || true)"

  if [ -n "${SOCK_GID}" ]; then
    # Create group matching docker.sock GID if missing
    if ! getent group "${SOCK_GID}" >/dev/null; then
      groupadd -g "${SOCK_GID}" dockersock || true
    fi

    # Add jenkins to the docker socket group
    GROUP_NAME="$(getent group "${SOCK_GID}" | cut -d: -f1)"
    usermod -aG "${GROUP_NAME}" jenkins || true
  fi
fi

# Run Jenkins as the jenkins user
exec gosu jenkins /usr/local/bin/jenkins.sh