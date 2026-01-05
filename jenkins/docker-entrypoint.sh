#!/usr/bin/env bash
set -e

# Align docker socket permissions so the 'jenkins' user can access Docker
if [ -S /var/run/docker.sock ]; then
  SOCK_GID="$(stat -c '%g' /var/run/docker.sock || true)"

  if [ -n "${SOCK_GID}" ]; then
    # Create a group with the same GID as the docker socket if it doesn't exist
    if ! getent group "${SOCK_GID}" >/dev/null; then
      groupadd -g "${SOCK_GID}" dockersock || true
    fi

    # Add jenkins user to that group name
    GROUP_NAME="$(getent group "${SOCK_GID}" | cut -d: -f1)"
    usermod -aG "${GROUP_NAME}" jenkins || true
  fi
fi

# Drop privileges and run Jenkins as the jenkins user
exec gosu jenkins /usr/local/bin/jenkins.sh