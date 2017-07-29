package com.trch.patternmatching;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by thomashutchinson on 29/07/2017.
 */
public abstract class AbstractPatternMatcher<Request, Response> {

    public Response call(Request request){
        return invokeMethod("invoke", request);
    }

    /**
     *
     * @param request representing a super type. If request
     * can't be mapped to an invoke method then this one
     * will called.
     * @return
     */
    protected abstract Response invoke(Request request);

    private Response invokeMethod(String methodName, Request request) {
        Method method = null;
        try {
            method = this.getClass().getDeclaredMethod(methodName, request.getClass());
            return (Response) method.invoke(this, request);
        } catch (NoSuchMethodException e) {
            //request can't be mapped to method
            //fallback to invoke method
            return invoke(request);
        } catch (IllegalAccessException e) {
           throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
