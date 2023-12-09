export const ApiRequestTypeMap = {
  GET: 'GET',
  POST: 'POST',
  PUT: 'PUT',
  DELETE: 'DELETE',
};

export const ApiRequestContentTypeMap = {
  'application/json': 'application/json',
  'application/x-www-form-urlencoded': 'application/x-www-form-urlencoded',
};

export type ApiRequestType = keyof typeof ApiRequestTypeMap;
export type ApiRequestContentType = keyof typeof ApiRequestContentTypeMap;
