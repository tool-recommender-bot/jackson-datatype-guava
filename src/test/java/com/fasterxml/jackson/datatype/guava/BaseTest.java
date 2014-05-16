package com.fasterxml.jackson.datatype.guava;

import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.guava.GuavaModule;

public abstract class BaseTest extends junit.framework.TestCase
{
    protected BaseTest() { }
    
    protected ObjectMapper mapperWithModule()
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new GuavaModule());
        return mapper;
    }

    protected String aposToQuotes(String json) {
        return json.replace("'", "\"");
    }

    public String quote(String str) {
        return '"'+str+'"';
    }

    protected void verifyException(Throwable e, String... matches)
    {
        String msg = e.getMessage();
        String lmsg = (msg == null) ? "" : msg.toLowerCase();
        for (String match : matches) {
            String lmatch = match.toLowerCase();
            if (lmsg.indexOf(lmatch) >= 0) {
                return;
            }
        }
        fail("Expected an exception with one of substrings ("+Arrays.asList(matches)+"): got one with message \""+msg+"\"");
    }
}
