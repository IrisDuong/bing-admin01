
export const LANG_CODE = [{langCode:"vi",langName:"Vietnam"},{langCode:"en",langName:"English"}];
export const CURRENT_LANG_CODE  = "vi";
export const LOCAL_LOGIN_NAME = "loggedInfo"
export const ACCESS_TOKEN = 'accessToken';
export const REFRESH_TOKEN = 'refreshToken';
// export const BASE_API_URL = '/';
export const BASE_API_URL = 'http://localhost:8086';
export const OAUTH2_REDIRECT_URI = 'http://localhost:3000/oauth2/redirect'
export const GOOGLE_AUTH_URL = BASE_API_URL + '/oauth2/authorize/google?redirect_uri=' + OAUTH2_REDIRECT_URI;
// export const FACEBOOK_AUTH_URL = BASE_API_URL + '/oauth2/authorize/facebook?redirect_uri=' + OAUTH2_REDIRECT_URI;
// export const GITHUB_AUTH_URL = BASE_API_URL + '/oauth2/authorize/github?redirect_uri=' + OAUTH2_REDIRECT_URI;