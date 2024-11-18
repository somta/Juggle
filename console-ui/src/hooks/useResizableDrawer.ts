import {computed, onUnmounted, ref} from 'vue';

export function useResizableDrawer(props: any, emit: any, drawerSize: any) {
    const resizeBar = ref<HTMLElement | null>(null);

    let startX = 0;
    let startY = 0;
    let initialSize = 0;

    function onMouseMove(e: MouseEvent) {
        if (props.direction === 'ltr' || props.direction === 'rtl') {
            const deltaX = props.direction === 'ltr' ? e.clientX - startX : startX - e.clientX;
            drawerSize.value = Math.max(200, initialSize + deltaX);
        } else if (props.direction === 'ttb' || props.direction === 'btt') {
            const deltaY = props.direction === 'ttb' ? e.clientY - startY : startY - e.clientY;
            drawerSize.value = Math.max(200, initialSize + deltaY);
        }
    }

    function onMouseUp() {
        document.removeEventListener('mousemove', onMouseMove);
        document.removeEventListener('mouseup', onMouseUp);
        if (props.drawerKey) {
            setDrawerSize(props.drawerKey, drawerSize.value);
        }
    }

    function onMouseDown(e: MouseEvent) {
        e.preventDefault();
        startX = e.clientX;
        startY = e.clientY;
        initialSize = drawerSize.value;
        document.addEventListener('mousemove', onMouseMove);
        document.addEventListener('mouseup', onMouseUp);
    }

    onUnmounted(() => {
        document.removeEventListener('mousemove', onMouseMove);
        document.removeEventListener('mouseup', onMouseUp);
    });

    function onClosed() {
        emit('closed');
        emit('update:modelValue', false);
    }

    const drawerClass = computed(() => {
        return `resize-drawer-${props.direction}`;
    });

    const resizeBarClass = computed(() => {
        return `resize-bar resize-bar-${props.direction}`;
    });

    return {
        resizeBar,
        onMouseDown,
        onClosed,
        drawerClass,
        resizeBarClass,
    };
}

export function getDrawerSize(drawerKey: string, defaultSize: number) {
    if (!drawerKey) return defaultSize;
    const drawList = JSON.parse(localStorage.getItem('drawList') || '[]');
    const drawer = drawList.find((item: any) => item.key === drawerKey);
    return drawer ? drawer.size : defaultSize;
}

export function setDrawerSize(drawerKey: string, size: number) {
    if (!drawerKey) return;
    const drawList = JSON.parse(localStorage.getItem('drawList') || '[]');
    const drawer = drawList.find((item: any) => item.key === drawerKey);
    if (drawer) {
        drawer.size = size;
    } else {
        drawList.push({key: drawerKey, size: size});
    }
    localStorage.setItem('drawList', JSON.stringify(drawList));
}