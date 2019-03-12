package com.ym.smdsj.util;

import java.util.regex.Pattern;

/**
 * Web防火墙工具类
 * @author ym
 * @date 2019/3/12
 **/
public class XssUtil {

    private static Pattern SCRIPT_ONE_PATTERN = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
    private static Pattern SCRIPT_TWO_PATTERN = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
    private static Pattern SCRIPT_THREE_PATTERN = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE
            | Pattern.MULTILINE | Pattern.DOTALL);
    private static Pattern EVAL_PATTERN = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE);
    private static Pattern EXPRESSION_PATTERN = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE
            | Pattern.MULTILINE | Pattern.DOTALL);
    private static Pattern JAVASCRIPT_PATTERN = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
    private static Pattern VBSCRIPT_PATTERN = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
    private static Pattern ONLOLAD_PATTERN = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE
            | Pattern.MULTILINE | Pattern.DOTALL);


    /* *
     * @Description 过滤XSS脚本内容
     * @Param [value]
     * @Return java.lang.String
     */
    private static String stripXSS(String value) {
        String rlt = null;

        if (null != value) {
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);

            // Avoid null characters
            rlt = value.replaceAll("", "");

            // Avoid anything between script tags
//            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            rlt = SCRIPT_ONE_PATTERN.matcher(rlt).replaceAll("");

            // Avoid anything in a src='...' type of expression
			/*scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE
					| Pattern.MULTILINE | Pattern.DOTALL);
			rlt = scriptPattern.matcher(rlt).replaceAll("");

			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE
					| Pattern.MULTILINE | Pattern.DOTALL);
			rlt = scriptPattern.matcher(rlt).replaceAll("");*/

            // Remove any lonesome </script> tag
//            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            rlt = SCRIPT_TWO_PATTERN.matcher(rlt).replaceAll("");

            // Remove any lonesome <script ...> tag
//            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE
//                    | Pattern.MULTILINE | Pattern.DOTALL);
            rlt = SCRIPT_THREE_PATTERN.matcher(rlt).replaceAll("");

            // Avoid eval(...) expressions
//            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE
//                    | Pattern.MULTILINE | Pattern.DOTALL);
            rlt = EVAL_PATTERN.matcher(rlt).replaceAll("");

            // Avoid expression(...) expressions
//            scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE
//                    | Pattern.MULTILINE | Pattern.DOTALL);
            rlt = EXPRESSION_PATTERN.matcher(rlt).replaceAll("");

            // Avoid javascript:... expressions
//            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            rlt = JAVASCRIPT_PATTERN.matcher(rlt).replaceAll("");

            // Avoid vbscript:... expressions
//            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            rlt = VBSCRIPT_PATTERN.matcher(rlt).replaceAll("");

            // Avoid onload= expressions
//            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE
//                    | Pattern.MULTILINE | Pattern.DOTALL);
            rlt = ONLOLAD_PATTERN.matcher(rlt).replaceAll("");
        }

        return rlt;
    }

    /* *
     * @Description 过滤SQL注入内容
     * @Param [value]
     * @Return java.lang.String
     */
    private static String stripSqlInjection(String value) {
        return (null == value) ? null : value.replaceAll("('.+--)|(--)|(%7C)", ""); //value.replaceAll("('.+--)|(--)|(\\|)|(%7C)", "");
    }

    /* *
     * @Description 过滤SQL 和 XSS注入内容
     * @Param [value]
     * @Return java.lang.String
     */
    public static String stripSqlXss(String value) {
        return stripXSS(stripSqlInjection(value));
    }

}

