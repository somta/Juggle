import typescript from '@rollup/plugin-typescript';
import commonjs from '@rollup/plugin-commonjs';

// rollup.config.mjs
export default {
  input: 'src/index.ts',
  output: [
    {
      file: 'cjs/index.js',
      format: 'cjs',
    },
    {
      file: 'esm/index.js',
      format: 'es',
    },
  ],
  plugins: [
    commonjs(),
    typescript({
      compilerOptions: { lib: ['es5', 'es6'], target: 'es5' },
    }),
  ],
};
