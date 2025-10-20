const defaultChars = {
    numbers: '0123456789',
    lowercase: 'abcdefghijklmnopqrstuvwxyz',
    uppercase: 'ABCDEFGHIJKLMNOPQRSTUVWXYZ',
    symbols: '!@#$%^&*()-_=+[]{}|;:,.<>?/',
};

export function isEmpty(val: any) {
    return val === undefined || val === null || val === '';
}

/**
 * 生成指定长度的随机字符串
 * @param length 字符串长度
 * @param options 选项，控制包含哪些字符类型
 */
export function generateRandomValue(length: number = 4,
    options: {
    numbers?: boolean;
    lowercase?: boolean;
    uppercase?: boolean;
    symbols?: boolean;
} = {}
): string {
    // 合并默认选项
    const { numbers = true, lowercase = true, uppercase = false, symbols = false } = options;

    // 构建可用字符集
    let chars = '';
    if (numbers) chars += defaultChars.numbers;
    if (lowercase) chars += defaultChars.lowercase;
    if (uppercase) chars += defaultChars.uppercase;
    if (symbols) chars += defaultChars.symbols;

    if (chars.length === 0) {
        throw new Error('至少需要启用一种字符类型（数字、小写、大写、符号）');
    }

    // 生成随机字符串
    let result = '';
    for (let i = 0; i < length; i++) {
        const randomIndex = Math.floor(Math.random() * chars.length);
        result += chars[randomIndex];
    }

    return result;
}

export function safeTrim(str: string | null | undefined): string {
    return str ? str.trim() : '';
}