//package fr.treeptik.service;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
//import org.apache.commons.lang.StringEscapeUtils;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Service;
//
//import fr.treeptik.exception.ServiceException;
//
//@Service
//@Scope(value = "singleton")
//public class ProtectService<T> {
//
//	
////	public T protect(T object) throws ServiceException{
////		Field[] declaredFields = object.getClass().getDeclaredFields();
////		for (Field field : declaredFields) {
////			if (field.getType().equals(String.class)){
////				field.setAccessible(true);
////				try {
////					field.set(object, StringEscapeUtils.escapeHtml((String) field.get(object)));
////				} catch (IllegalArgumentException | IllegalAccessException e) {
////					throw new ServiceException("Erreur protect object");
////				}
////			}
////		
////		}
////		return object;
////	}
////	
////	public T unProtect(T object) throws ServiceException{
////		Field[] declaredFields = object.getClass().getDeclaredFields();
////		for (Field field : declaredFields) {
////			if (field.getType().equals(String.class)){
////				field.setAccessible(true);
////				try {
////					field.set(object, StringEscapeUtils.unescapeHtml((String) field.get(object)));
////				} catch (IllegalArgumentException | IllegalAccessException e) {
////					throw new ServiceException("Erreur unProtect object");
////				}
////			}
////		
////		}
////		return object;
////	}
//	
//	
//	public T protect(T object) throws ServiceException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		if (object == null) return null;
//		System.out.println("Type = " + object.getClass().toString());
//		
//		Class<? extends Object> class1 = object.getClass();
//		Method[] methods = class1.getMethods();
//		for (Method method : methods) {
//
//			if (method.getReturnType().equals(void.class) && method.getName().length() > 3  && method.getName().substring(0, 3).equals("set")) {
//
//				
//				if (method.getParameterCount() == 1){
//					if (method.getParameterTypes()[0].equals(String.class)){
//
//						String get = getObjet(object, class1, method).toString();			
//
//						
//						System.out.println(method.getName() + " " + get);
//
//						
//						
//					} else {
//						
//						Object get = getObjet(object, class1, method);
//						System.out.println(method.getName() + " " + get);
//
//			
//						ProtectService<Object> ps = new ProtectService<>();
//						get = ps.protect(get);
//						
//						
//					}
//					
//					
//				}
//				
//				
//				
//			}
//
//			
////			if (method.getReturnType().equals(void.class) && method.getName().length() > 3  && method.getName().substring(0, 3).equals("set")) {
////				try {
////					String methodName = method.getName();
////					
////					System.out.println(methodName);
////					
////					String shortMethodName = methodName.substring(4, methodName.length() - 4);
////					
////					System.out.println(shortMethodName);
////					
////					Method getMethod = object.getClass().getMethod("get" + shortMethodName);
////					String get = getMethod.invoke(object).toString();
////					
////					System.out.println(get);
////					
////					method.invoke(object, StringEscapeUtils.escapeHtml((String) get));
////				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
////					throw new ServiceException("Erreur protect object");
////				}
////			}
//
//		}
//		return object;
//	}
//	
//	private Object getObjet(T object, Class<? extends Object> contener, Method setter) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
//		String methodName = setter.getName();
//		String shortMethodName = methodName.substring(3, methodName.length());
//
//		Method getter = contener.getMethod("get" + shortMethodName);
//		return getter.invoke(object);
//	}
//	
//}
