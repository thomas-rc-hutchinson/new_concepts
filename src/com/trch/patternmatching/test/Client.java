package com.trch.patternmatching.test;

import com.trch.patternmatching.AbstractPatternMatcher;

/**
 * Created by thomashutchinson on 29/07/2017.
 */
public class Client {
    public static interface IResult {
    }

    public static class GoodResult implements IResult {
    }

    public static class BadResult implements IResult {
    }

    public static class NotFoundResult implements IResult {
    }

    public static class OtherResult implements IResult {
    }

    public static class Response {
        final int status;

        public Response(int status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "status=" + status +
                    '}';
        }
    }

    public static class ResponseFactory extends AbstractPatternMatcher<IResult, Response> {

        @Override
        public Response invoke(IResult result) {
            throw new IllegalArgumentException("not clause matching " + result);
        }

        public Response invoke(GoodResult goodResult){
            return new Response(200);
        }

        public Response invoke(BadResult goodResult){
            return new Response(500);
        }

        public Response invoke(NotFoundResult goodResult){
            return new Response(404);
        }
    }

    public static void main(String[] args){
        ResponseFactory responseFactory = new ResponseFactory();
        IResult result = new GoodResult();
        IResult result2 = new BadResult();
        IResult result3 = new NotFoundResult();
        IResult result4 = new OtherResult(); //will throw exception
        System.out.println(responseFactory.call(result));
        System.out.println(responseFactory.call(result2));
        System.out.println(responseFactory.call(result3));
        System.out.println(responseFactory.call(result4));
    }

}
