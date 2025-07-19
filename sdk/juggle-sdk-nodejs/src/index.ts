import * as request from './utils/request';
import {IConfig} from "./IConfig";


const TRIGGER_FLOW_URL = '/open/v1/flow/trigger';
const GET_ASYNC_FLOW_RESULT_URL = '/open/v1/flow/getAsyncFlowResult';

export type FlowTriggerDataParam = Record<string, any>;

export default class JuggleClient {
  config: IConfig;
  constructor(config: IConfig) {
    this.config = config;
  }

  async triggerFlow(
    flowVersion: string,
    flowKey: string,
    flowData: FlowTriggerDataParam
  ) {
    const url = `${this.config.serverAddr}${TRIGGER_FLOW_URL}/${flowVersion}/${flowKey}`;

    const response = await request.post(url, flowData,{
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
