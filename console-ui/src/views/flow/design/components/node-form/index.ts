
import StartForm from './StartForm.vue';
import EndForm from './EndForm.vue';
import ConditionForm from './ConditionForm.vue';
import MethodForm from './MethodForm.vue';
import CodeForm from "./CodeForm.vue";
import MySqlForm from "./MySqlForm.vue";
import OpenAiForm from "./OpenAiForm.vue";
import { ElementType } from '../../types';



const nodeFormMap = {
  [ElementType.START]: StartForm,
  [ElementType.END]: EndForm,
  [ElementType.CONDITION]: ConditionForm,
  [ElementType.METHOD]: MethodForm,
  [ElementType.CODE]: CodeForm,
  [ElementType.MYSQL]: MySqlForm,
  [ElementType.AI]: OpenAiForm,
};

export function getNodeForm(type: ElementType) {
  return nodeFormMap[type as keyof typeof nodeFormMap];
}
