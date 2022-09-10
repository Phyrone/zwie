tauricon "$1"
pwa-asset-generator \
  --path /pwa \
  --splash-only\
  "$1" static/pwa

tauricon --target static/icons/ "$1"