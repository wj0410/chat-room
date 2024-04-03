import { ref } from "vue";
import useUserStore from "@/store/user";
import MessageProp from "@/prop/MessageProp";
const userStore = useUserStore();

export const usePageLogic = () => {
    const sendTextRef = ref('');

    // ctrl+enter
    const handleCtrlEnter = (event: KeyboardEvent) => {
        const input = event.target as HTMLInputElement;
        if (!input) {
            return;
        }
        const start = input.selectionStart ?? 0;
        const end = input.selectionEnd ?? 0;
        const value = sendTextRef.value;
        event.preventDefault(); // 阻止默认的换行行为
        sendTextRef.value = value.substring(0, start) + '\n' + value.substring(end);
        input.selectionStart = input.selectionEnd = start + 1; // 将光标移至新的一行
    }

    // 粘贴
    const handlePaste = (event: ClipboardEvent) => {
        const items = (event.clipboardData || (event as any).clipboardData).items;
        for (const item of items) {
            if (item.kind === 'file' && item.type.includes('image')) {
                const file = item.getAsFile();
                // 处理图片文件：例如上传、显示预览等操作
                processImage(file);
            }
        }
    }
    // 粘贴图片
    const processImage = (imageFile: File) => {
        // 这里只是将图片转成 base64 编码，实际应用中可能需要上传到服务器或进行其他处理
        const reader = new FileReader();
        reader.readAsDataURL(imageFile);
        reader.onload = () => {
            console.log(reader.result);
        };
    }
    // 消息样式判断
    const msgStyle = (item: MessageProp) => {
        if (item.username === userStore.loginUser?.username) {
            return 'message outgoing'
        } else {
            return 'message incoming'
        }
    }
    // emoji表情
    const emojiShowRef = ref(false)
    const showEmoji = () => {
        emojiShowRef.value = !emojiShowRef.value
    }
    // 选中表情
    const chooseEmoji = (item: string) => {
        console.log(item)
    }
    return { sendTextRef, emojiShowRef, handleCtrlEnter, handlePaste, msgStyle, showEmoji, chooseEmoji }
}

// 拖拽功能
export const useDrag = () => {
    const chatHeight = ref(500)
    const dragSwitch = ref(false)
    // 点击拖拽时x轴位置
    const startY = ref(0);
    // line点击时的高度
    const dragHeight = ref(0);

    const mousedown = (event: any) => {
        event.preventDefault();
        dragSwitch.value = true
        const chatHeight: any = document.querySelector('.chat-message-area')
        startY.value = event.clientY || event.touches[0].clientY;
        dragHeight.value = chatHeight.offsetHeight;
    }
    const mousemove = (event: any) => {
        if (!dragSwitch.value) {
            return
        }
        const clientY = event.clientY || event.touches[0].clientY;
        const offsetY = clientY - startY.value;

        let newDragHeight = dragHeight.value + offsetY;
        // 添加最小高度和最大高度限制
        if (newDragHeight < 300) {
            newDragHeight = 300;
        } else if (newDragHeight > 500) {
            newDragHeight = 500;
        }
        chatHeight.value = newDragHeight
    }
    const mouseup = () => {
        dragSwitch.value = false
    }
    return { chatHeight, mousedown, mousemove, mouseup }
}