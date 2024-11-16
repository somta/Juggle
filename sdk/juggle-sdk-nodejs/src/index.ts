import * as request from './utils/request';
import qs from 'query-string';
import {IConfig} from "./IConfig";


const TRIGGER_FLOW_URL = '/v1/open/flow/trigger';
const GET_ASYNC_FLOW_RESULT_URL = '/v1/open/flow/getAsyncFlowResult';

export type FlowTriggerDataParam = Record<string, any>;

export default class JuggleClient {
  config: IConfig;
  constructor(config: IConfig) {
    this.config = config;
  }

  async triggerFlow(
    flowVersion: string,
    flowKey: string,
    triggerData: FlowTriggerDataParam,
    flowData?: Record<string, any>
  ) {
    const url =
      `${this.config.serverAddr}${TRIGGER_FLOW_URL}/${flowVersion}/${flowKey}` +
      qs.stringify(triggerData);

    const response = await request.get(url, {
      'Juggle-Token': this.config.accessToken,
    });
    return response;
  }

  async getAsyncFlowResult(flowInstanceId: string) {
    const response = await request.get(
      `${this.config.serverAddr}${GET_ASYNC_FLOW_RESULT_URL}/${flowInstanceId}`,
      {
        'Juggle-Token': this.config.accessToken,
      }
    );

    return response;
  }
}
