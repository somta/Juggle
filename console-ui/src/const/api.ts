
export const ApiRequestTypeMap = {
  GET: 'GET',
  POST: 'POST',
  PUT: 'PUT',
  DELETE: 'DELETE',
};

export const ApiRequestContentTypeMap = {
  'application/json': 'APPLICATION_JSON',
  'application/x-www-form-urlencoded': 'APPLICATION_FORM',
};

export type ApiRequestType = keyof typeof ApiRequestTypeMap;
export type ApiRequestContentType = keyof typeof ApiRequestContentTypeMap;
