import { request, ResponseResult } from '@/service/base';
import {JUGGLE_API_PREFIX} from "@/const/application.ts";

export function listDataType(): ResponseResult {
  return request.get(JUGGLE_API_PREFIX+'/dataType/list');
}
