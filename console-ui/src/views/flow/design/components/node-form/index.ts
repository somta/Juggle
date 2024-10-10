import ConditionForm from './ConditionForm.vue';
import MethodForm from './MethodForm.vue';
import CodeForm from './CodeForm.vue';
import MySqlForm from './MySqlForm.vue';
import { ElementType } from '../../types';
import AssignForm from "./AssignForm.vue";

const nodeFormMap = {
  [ElementType.CONDITION]: ConditionForm,
  [ElementType.METHOD]: MethodForm,
  [ElementType.CODE]: CodeForm,
  [ElementType.ASSIGN]: AssignForm,
  [ElementType.MYSQL]: MySqlForm,
};

export function getNodeForm(type: ElementType) {
  return nodeFormMap[type as keyof typeof nodeFormMap];
}
