package net.somta.juggle.core.executor.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.Script;
import net.somta.common.encrypt.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GroovyScriptCompiler {
    private static final Logger log = LoggerFactory.getLogger(GroovyScriptCompiler.class);

    /**
     * 用于缓存已编译的脚本类
     * TODO 考虑改用LRU缓存
     */
    protected static Map<String, Class<?>> scriptCache = new ConcurrentHashMap<>();

    /**
     * 编译 Groovy 脚本并缓存已编译的类
     * @param script Groovy 脚本
     * @return 已编译的脚本类
     */
    public static Class<?> compile(String script) {
        String scriptMd5 = Md5Util.encrypt(script);
        // 使用 ConcurrentHashMap 的 computeIfAbsent 原子操作进行缓存检查和编译
        return scriptCache.computeIfAbsent(scriptMd5, key -> {
            // 编译脚本
            Class<?> scriptClass = null;
            try (GroovyClassLoader groovyClassLoader = new GroovyClassLoader()) {
                scriptClass = groovyClassLoader.parseClass(script);
                log.info("Groovy类缓存数量：{}", scriptCache.size());
                return scriptClass;
            } catch (IOException e) {
                log.warn("Groovy类加载器资源释放异常：{}", e.getMessage(), e);
                return scriptClass;
            }
        });
    }

    /**
     * 执行已编译的脚本类，传入 Binding 变量
     * @param scriptClass 编译后的脚本类
     * @param binding 传入的 Binding 对象，包含变量
     * @return 脚本执行结果
     * @throws Exception 如果执行失败抛出异常
     */
    public static Object executeScript(Class<?> scriptClass, Binding binding) throws Exception {
        // 创建脚本实例
        Script scriptInstance = (Script) scriptClass.getDeclaredConstructor().newInstance();
        // 设置 Binding，传入外部变量
        scriptInstance.setBinding(binding);
        // 执行脚本
        return scriptInstance.run();
    }
}
