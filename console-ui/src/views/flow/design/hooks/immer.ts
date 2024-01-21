import { produce } from 'immer';
import { ShallowRef, shallowRef } from 'vue'

export function useImmer<T extends any>(baseState: T) {
  const state = shallowRef(baseState)
  const update = (updater: (draft: T) => void) => {
    state.value = produce(state.value, updater)
  }
  return [state, update] as [ShallowRef<T>, typeof update];
}