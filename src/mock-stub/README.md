From the node directory (current directory), run ``npm install`` to install all node dependent module, these modules are ignored in git.

From the node directory (current directory), run ``npm run start`` to start mock server.


cf login -a api.system.np.sdppcf.com --sso
cf push

##Create Routes
cf map-route svc-cip-smarthome-mock-server cip.api.id.np.corp.telstra.com --hostname dev --path /v2/oauth/token
cf map-route svc-cip-smarthome-mock-server cip.api.id.np.corp.telstra.com --hostname dev --path /v1/digital-identity
cf map-route svc-cip-smarthome-mock-server cip.api.id.np.corp.telstra.com --hostname dev --path /application/ddc
cf map-route svc-cip-smarthome-mock-server cip.api.id.np.corp.telstra.com --hostname dev --path /application/smart-home
cf map-route svc-cip-smarthome-mock-server cip.api.id.np.corp.telstra.com --hostname dev --path /v1/okapi-microauth/keys