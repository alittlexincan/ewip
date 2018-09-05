package com.hyt.server.aspect;

import com.alibaba.fastjson.JSONObject;
import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;
@SuppressWarnings("unchecked")
@Component
@Aspect
public class EmergencyAspect {

    /**
     * 接入国突对接接口
     */
//    @Autowired
//    private IRecordService recordService;


    @Pointcut("@annotation(com.hyt.server.anno.Emergency)")
    public void emergency(){}

    /**
     * 环绕通知 @Around  ， 当然也可以使用 @Before (前置通知)  @After (后置通知)
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("emergency()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }

    /**
     * 在环绕通知之后执行
     * @param joinPoint
     * @throws Throwable
     */
    @After("emergency()")
    public void before(JoinPoint joinPoint){
        try{

            String classType = joinPoint.getTarget().getClass().getName();
            Class<?> clazz = Class.forName(classType);
            String clazzName = clazz.getName();
            String methodName = joinPoint.getSignature().getName(); //获取方法名称
            Object[] args = joinPoint.getArgs();//参数
            JSONObject json = getFieldsName(this.getClass(), clazzName, methodName, args);//获取被切参数名称及参数值

            // 如果record==0，则不需要国突上报，否则上报或备案
            int record = json.getInteger("record");
            if(record == 1){
//            int num = this.recordService.record(json);
//            if(num == 1) {
//                System.out.println("====国突对接成功====");
//            }else {
//                System.out.println("====国突对接失败====");
//            }
            }

        }catch (Exception e){
            e.getMessage();
        }

    }

    /**
     * 通过反射机制 获取被切参数名以及参数值
     *
     * @param cls
     * @param clazzName
     * @param methodName
     * @param args
     * @return
     * @throws NotFoundException
     */
    private JSONObject getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws NotFoundException {
        JSONObject json = new JSONObject();
        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);
        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;

        for (int i = 0; i < cm.getParameterTypes().length; i++) {
            json.put(attr.variableName(i + pos), args[i]);
        }
        Map<String, Object> map = (Map<String, Object>)json.get("map");
        return new JSONObject(map);
    }
}
