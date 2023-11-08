/// <reference types="vite/client" />

interface ImportMetaEnv {
  readonly VITE_API_PROXY: string
}

interface ImportMeta {
  readonly env: ImportMetaEnv
}
