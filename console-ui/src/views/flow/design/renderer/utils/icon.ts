import { D3Element } from '../../types';

const icons: Record<string, string> = {
  edit: 'M818.77 256L646.2 106.67a64 64 0 00-91.52 6.61L78.29 685.87A64 64 0 0064 725.33l-2.56 198.4a32.21 32.21 0 0031.79 32.43l186.45 1.5a64 64 0 0049.28-21.34L825.39 345.6a64 64 0 00-6.62-89.6zM270.08 872.96l-123.1-1.07 2.35-137.17 457.39-549.97 139.95 121.38zm238.08.21H928q32 0 32 32v21.34q0 32-32 32H508.16q-32 0-32-32v-21.34q0-32 32-32z',
  plus: 'M863.79 469.12H554.67V160c0-17.7-14.3-32-32-32h-21.34c-17.7 0-32 14.3-32 32v309.12H160.21c-17.7 0-32 14.3-32 32v21.33c0 17.71 14.3 32 32 32h309.12v309.12c0 17.71 14.3 32 32 32h21.34c17.7 0 32-14.29 32-32V554.45h309.12c17.7 0 32-14.29 32-32v-21.33c0-17.7-14.3-32-32-32z',
  delete: 'M929.92 192h-832a32 32 0 00-32 32v21.33a32 32 0 0032.64 32h53.76V896a64 64 0 0064 64h594.35a64 64 0 0064-64V277.33h55.89a32 32 0 0032-32V224a32 32 0 00-32.64-32zM789.33 874.67H237.65V277.33h551.68zM354.35 63.36h320q32 0 32 32v21.33q0 32-32 32h-320q-32 0-32-32V95.36q0-32 32-32zm260.26 364.8h21.34q32 0 32 32v251.73q0 32-32 32H614.6q-32 0-32-32V460.16q0-32 32-32zm-222.08 0h21.34q32 0 32 32v251.73q0 32-32 32h-21.34q-32 0-32-32V460.16q0-32 32-32z',
  start: 'M512 0C230.4 0 0 230.4 0 512s230.4 512 512 512 512-230.4 512-512S793.6 0 512 0z m204.8 576l-215.04 156.16c-12.8 7.68-25.6 12.8-40.96 12.8-38.4 0-69.12-30.72-69.12-69.12V360.96c0-15.36 5.12-28.16 12.8-40.96 23.04-30.72 66.56-38.4 97.28-15.36L716.8 460.8l15.36 15.36c23.04 33.28 15.36 76.8-15.36 99.84z',
  end:'M512 60.229818A451.723636 451.723636 0 0 0 60.229818 512 451.723636 451.723636 0 0 0 512 963.770182 451.723636 451.723636 0 0 0 963.770182 512c0-249.530182-202.333091-451.770182-451.770182-451.770182z m-28.485818 231.749818a33.978182 33.978182 0 0 1 10.007273-24.529454 32.535273 32.535273 0 0 1 24.15709-10.333091 33.047273 33.047273 0 0 1 24.715637 10.333091 33.978182 33.978182 0 0 1 10.007273 24.529454v206.661819a32.861091 32.861091 0 0 1-10.007273 24.203636 33.605818 33.605818 0 0 1-24.715637 9.960727 32.861091 32.861091 0 0 1-24.203636-10.007273 33.047273 33.047273 0 0 1-9.960727-24.203636v-206.661818z m258.653091 334.289455a249.762909 249.762909 0 0 1-52.224 77.265454 248.459636 248.459636 0 0 1-77.265455 52.224 234.263273 234.263273 0 0 1-94.906182 19.269819 235.194182 235.194182 0 0 1-94.394181-19.269819 253.44 253.44 0 0 1-77.498182-52.224 241.058909 241.058909 0 0 1-71.307637-172.171636c0-19.269818 2.234182-38.074182 6.795637-56.552727 4.561455-18.478545 10.938182-36.072727 19.269818-52.782546 8.378182-16.709818 18.711273-32.302545 30.952727-46.778181 28.765091-33.605818 57.902545-47.104 67.165091-45.614546 9.262545 1.396364 16.802909 5.957818 22.621091 13.544727a32.581818 32.581818 0 0 1 6.516364 25.6 34.257455 34.257455 0 0 1-13.544728 22.807273c-22.807273 16.756364-40.308364 37.236364-52.456727 61.486546a172.916364 172.916364 0 0 0-18.245818 78.289454 173.149091 173.149091 0 0 0 50.874182 123.159273c15.825455 15.825455 34.304 28.299636 55.435636 37.515636 21.271273 9.262545 43.752727 13.963636 67.723636 13.963637 23.924364 0 46.545455-4.608 67.723637-13.963637a176.128 176.128 0 0 0 92.951273-92.951273c9.262545-21.178182 13.963636-43.752727 13.963636-67.723636 0-28.253091-6.609455-55.016727-19.642182-80.290909a169.658182 169.658182 0 0 0-54.923636-62.231273 33.000727 33.000727 0 0 1-14.429091-22.248727 34.210909 34.210909 0 0 1 27.927273-40.122182 34.210909 34.210909 0 0 1 26.158545 5.678546c16.290909 11.636364 30.859636 24.715636 43.566545 39.424 12.706909 14.708364 23.598545 30.533818 32.581819 47.569454a240.64 240.64 0 0 1 27.880727 111.895273v0.093091c0 33.978182-6.469818 65.629091-19.269818 95.138909z',
};

export function loadSvgIcon(svg: D3Element) {
  Object.keys(icons).forEach(key => {
    const iconSvg = svg
      .append('defs')
      .append('svg')
      .attr('id', 'icon-' + key)
      .attr('viewBox', '0 0 1024 1024');

    iconSvg.append('path').attr('d', icons[key]);
  });
}
