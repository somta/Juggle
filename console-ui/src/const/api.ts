
export const ApiRequestTypeMap = {
  GET: 'GET',
  POST: 'POST',
  PUT: 'PUT',
  DELETE: 'DELETE',
};

export type ApiRequestType = keyof typeof ApiRequestTypeMap;
