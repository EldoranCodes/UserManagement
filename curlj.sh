#!/bin/bash

# This script acts like curl but auto-prettifies JSON using jq

# If no arguments → show help
if [ $# -eq 0 ]; then
  echo "Usage: $0 [curl arguments]"
  echo "Example: $0 -X GET http://localhost:8080/users"
  exit 1
fi

# Forward all arguments directly to curl
curl -s "$@" | jq
