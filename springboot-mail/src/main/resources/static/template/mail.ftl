<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>Message Title</title>
</head>
<body class="jira" style="color: #333333; font-family: Arial, sans-serif; font-size: 14px; line-height: 1.429">
<table id="background-table" cellpadding="0" cellspacing="0" width="100%"
       style="border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f5f5f5; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt"
       bgcolor="#f5f5f5">
    <!-- header here -->
    <tbody>
    <tr>
        <td id="header-pattern-container" style="padding: 0px; border-collapse: collapse; padding: 10px 20px">
            <table id="header-pattern" cellspacing="0" cellpadding="0" border="0"
                   style="border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt">
                <tbody>
                <tr>

                    <td id="header-text-container" valign="middle"
                        style="padding: 0px; border-collapse: collapse; vertical-align: middle; font-family: Arial, sans-serif; font-size: 14px; line-height: 20px; mso-line-height-rule: exactly; mso-text-raise: 1px">
                        <a href="${(pipeline.pipelineUrl)!""}" style="color: #3b73af; text-decoration: none"><img
                            src="${(pipeline.pipelineIconUrl)!""}" height="16" width="16" border="0" align="absmiddle"
                            alt="Defect"> ${(pipeline.pipelineName)!""}</a></td>
                </tr>
                </tbody>
            </table>
        </td>
    </tr>
    <tr>
        <td id="email-content-container" style="padding: 0px; border-collapse: collapse; padding: 0 20px">
            <table id="email-content-table" cellspacing="0" cellpadding="0" border="0" width="100%"
                   style="border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-spacing: 0; border-collapse: separate">
                <tbody>
                <tr>
                    <!-- there needs to be content in the cell for it to render in some clients -->
                    <td class="email-content-rounded-top mobile-expand"
                        style="padding: 0px; border-collapse: collapse; color: #ffffff; padding: 0 15px 0 16px; height: 15px; background-color: #ffffff; border-left: 1px solid #cccccc; border-top: 1px solid #cccccc; border-right: 1px solid #cccccc; border-bottom: 0; border-top-right-radius: 5px; border-top-left-radius: 5px; height: 10px; line-height: 10px; padding: 0 15px 0 16px; mso-line-height-rule: exactly"
                        height="10" bgcolor="#ffffff">&nbsp;
                    </td>
                </tr>
                <tr>
                    <td class="email-content-main mobile-expand "
                        style="padding: 0px; border-collapse: collapse; border-left: 1px solid #cccccc; border-right: 1px solid #cccccc; border-top: 0; border-bottom: 0; padding: 0 15px 0 16px; background-color: #ffffff"
                        bgcolor="#ffffff">
                        <table class="page-title-pattern" cellspacing="0" cellpadding="0" border="0" width="100%"
                               style="border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt">
                            <tbody>
                            <tr>
                                <td class="page-title-pattern-first-line "
                                    style="padding: 0px; border-collapse: collapse; font-family: Arial, sans-serif; font-size: 14px; padding-top: 10px"> ${(pipeline.productName)!""}
                                    / ${(pipeline.deviceModel)!""} / ${(pipeline.androidVer)!""}
                                    / ${(pipeline.osVersion)!""} </td>
                            </tr>
                            <tr>
                                <td style="vertical-align: top;; padding: 0px; border-collapse: collapse; padding-right: 5px; font-size: 20px; line-height: 30px; mso-line-height-rule: exactly"
                                    class="page-title-pattern-header-container"><span class="page-title-pattern-header"
                                                                                      style="font-family: Arial, sans-serif; padding: 0; font-size: 20px; line-height: 30px; mso-text-raise: 2px; mso-line-height-rule: exactly; vertical-align: middle; color: #3b73af; text-decoration: none"> ${(pipeline.pipelineName)!""} test result is ${(pipeline.pipelineStatus)!""} </span>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                                <#if (pipeline.isSuccess!=1)>
                                <tr>
                                    <td class="email-content-main mobile-expand  wrapper-special-margin"
                                        style="padding: 0px; border-collapse: collapse; border-left: 1px solid #cccccc; border-right: 1px solid #cccccc; border-top: 0; border-bottom: 0; padding: 0 15px 0 16px; background-color: #ffffff; padding-top: 10px; padding-bottom: 5px"
                                        bgcolor="#ffffff">
                                        <table class="keyvalue-table"
                                               style="border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt">
                                            <tbody>
                                            <tr>
                                                <th style="color: #707070; font: normal 14px/20px Arial, sans-serif; text-align: left; vertical-align: top; padding: 2px 0">
                                                    Testcase:
                                                </th>
                                                <th style="color: #707070; font: normal 14px/20px Arial, sans-serif; text-align: left; vertical-align: top; padding: 2px 0">
                                                    Result:
                                                </th>
                                                <th style="color: #707070; font: normal 14px/20px Arial, sans-serif; text-align: left; vertical-align: top; padding: 2px 0">
                                                    Desc:
                                                </th>

                                            </tr>
                                                <#list pipeline.failList as data>
                                                    <tr>
                                                        <#if (data.testResult=='FAIL')>
                                                            <td style="padding: 0px; border-collapse: collapse; font: normal 14px/20px Arial, sans-serif; padding: 2px 0 2px 5px; vertical-align: top">
                                                                <span class="diffaddedchars"
                                                                      style="background-color:#ffe7e7;">${data.testCaseName}</span>
                                                            </td>
                                                            <td style="padding: 0px; border-collapse: collapse; font: normal 14px/20px Arial, sans-serif; padding: 2px 0 2px 5px; vertical-align: top">
                                                                <span class="diffaddedchars"
                                                                      style="background-color:#ffe7e7;">${data.testResult}</span>
                                                            </td>
                                                            <td style="padding: 0px; border-collapse: collapse; font: normal 14px/20px Arial, sans-serif; padding: 2px 0 2px 5px; vertical-align: top">
                                                                <span class="diffaddedchars"
                                                                      style="background-color:#ffe7e7;">${data.testResultDesc}</span>
                                                            </td>
                                                        <#elseif (data.testResult=='RUNNING')>
                                                            <td style="padding: 0px; border-collapse: collapse; font: normal 14px/20px Arial, sans-serif; padding: 2px 0 2px 5px; vertical-align: top">
                                                                <span class="diffaddedchars"
                                                                      style="background-color:#9acd32;">${data.testCaseName}</span>
                                                            </td>
                                                            <td style="padding: 0px; border-collapse: collapse; font: normal 14px/20px Arial, sans-serif; padding: 2px 0 2px 5px; vertical-align: top">
                                                                <span class="diffaddedchars"
                                                                      style="background-color:#9acd32;">${data.testResult}</span>
                                                            </td>
                                                            <td style="padding: 0px; border-collapse: collapse; font: normal 14px/20px Arial, sans-serif; padding: 2px 0 2px 5px; vertical-align: top">
                                                                <span class="diffaddedchars"
                                                                      style="background-color:#9acd32;">${data.testResultDesc}</span>
                                                            </td>
                                                        <#elseif (data.testResult=='SUCCESS')>
                                                            <td style="padding: 0px; border-collapse: collapse; font: normal 14px/20px Arial, sans-serif; padding: 2px 0 2px 5px; vertical-align: top">
                                                                <span class="diffaddedchars"
                                                                      style="background-color:#ddfade;">${data.testCaseName}</span>
                                                            </td>
                                                            <td style="padding: 0px; border-collapse: collapse; font: normal 14px/20px Arial, sans-serif; padding: 2px 0 2px 5px; vertical-align: top">
                                                                <span class="diffaddedchars"
                                                                      style="background-color:#ddfade;">${data.testResult}</span>
                                                            </td>
                                                            <td style="padding: 0px; border-collapse: collapse; font: normal 14px/20px Arial, sans-serif; padding: 2px 0 2px 5px; vertical-align: top">
                                                                <span class="diffaddedchars"
                                                                      style="background-color:#ddfade;">${data.testResultDesc}</span>
                                                            </td>
                                                        <#elseif (data.testResult=='SKIPPED')>
                                                            <td style="padding: 0px; border-collapse: collapse; font: normal 14px/20px Arial, sans-serif; padding: 2px 0 2px 5px; vertical-align: top">
                                                                <span class="diffaddedchars"
                                                                      style="background-color:#808080;">${data.testCaseName}</span>
                                                            </td>
                                                            <td style="padding: 0px; border-collapse: collapse; font: normal 14px/20px Arial, sans-serif; padding: 2px 0 2px 5px; vertical-align: top">
                                                                <span class="diffaddedchars"
                                                                      style="background-color:#808080;">${data.testResult}</span>
                                                            </td>
                                                            <td style="padding: 0px; border-collapse: collapse; font: normal 14px/20px Arial, sans-serif; padding: 2px 0 2px 5px; vertical-align: top">
                                                                <span class="diffaddedchars"
                                                                      style="background-color:#808080;">${data.testResultDesc}</span>
                                                            </td>
                                                        </#if>
                                                    </tr>
                                                </#list>

                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                                </#if>
                <!-- there needs to be content in the cell for it to render in some clients -->
                <tr>
                    <td class="email-content-rounded-bottom mobile-expand"
                        style="padding: 0px; border-collapse: collapse; color: #ffffff; padding: 0 15px 0 16px; height: 5px; line-height: 5px; background-color: #ffffff; border-top: 0; border-left: 1px solid #cccccc; border-bottom: 1px solid #cccccc; border-right: 1px solid #cccccc; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; mso-line-height-rule: exactly"
                        height="5" bgcolor="#ffffff">&nbsp;
                    </td>
                </tr>
                </tbody>
            </table>
        </td>
    </tr>
    <tr>
        <td id="footer-pattern" style="padding: 0px; border-collapse: collapse; padding: 12px 20px">
            <table id="footer-pattern-container" cellspacing="0" cellpadding="0" border="0"
                   style="border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt">
                <tbody>
                <tr>
                    <td id="footer-pattern-text" class="mobile-resize-text" width="100%"
                        style="padding: 0px; border-collapse: collapse; color: #999999; font-size: 12px; line-height: 18px; font-family: Arial, sans-serif; mso-line-height-rule: exactly; mso-text-raise: 2px">
                        This message was sent by Android BIFROST <span
                        id="footer-build-information">(${(pipeline.bifrostVer)!""})</span></td>
                    <td id="footer-pattern-logo-desktop-container" valign="top"
                        style="padding: 0px; border-collapse: collapse; padding-left: 20px; vertical-align: top">
                        <table style="border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt">
                            <tbody>
                            <tr>
                                <td id="footer-pattern-logo-desktop-padding"
                                    style="padding: 0px; border-collapse: collapse; padding-top: 3px"><img
                                    id="footer-pattern-logo-desktop" src="${(pipeline.bifrostLogoUrl)!""}"
                                    alt="Bifrost logo" title="Bifrost logo" width="36" height="36" class="image_fix">
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                </tbody>
            </table>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>
