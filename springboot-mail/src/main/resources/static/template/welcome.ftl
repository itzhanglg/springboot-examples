<!doctype html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>Welcome</title>
</head>
<body>
<div class="container"
     style=" width: 800px;
         height: auto;
         margin: 0 auto;
         border: 1px #dddddd solid;
         border-top: 4px #3498db solid;
         font: 14px Microsoft Yahei;
         color: #333;">
    <div class="main" style="padding: 0 15px;">
        <div style="
                 border: 1px #f39d12 dashed;
                 background-color: #fffdf4;
                 margin: 20px 0;
                 border-radius: 6px;">
            <table width="100%" style="border: none">
                <tr>
                    <td style="width: 20%; text-align: center; padding-top: 25px">
                        <img src="${path}/image/icon_email_prompt.png" alt="">
                    </td>
                    <td colspan="2" style="font-size: 18px; line-height: 1.6;padding-top: 30px">
                        <div style="text-indent: 36px; padding-right: 24px">
                        ${mail.content}
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="footer" style="margin: auto;
                            padding: 15px 0 15px 15px;
                            background-color: #fafafa;
                            border-top: 1px #ddd solid;
                            color: #333;
                            height: auto;
                            zoom: 1;
                            overflow: auto;">
        <table style="width: 100%">
            <tr>
                <td style="width: 60%">
                    <h4 style="font-size: 16px;
                             margin: 10px 0;">
                        传音控股科技有限公司
                    </h4>
                    <p>
                        公司地址：
                        <b>上海市浦东新区张江镇</b>
                    </p>
                    <p>
                        官方网站：
                        <a href="http://www.transsion.com/"
                           style="text-decoration: none; color: #333; font-weight: bold;">www.transsion.com/</a>
                    </p>
                    <p>
                        服务热线：
                        <b>
                            <span style="display: inline-block; margin-right: 16px;">86-755-33979200</span>
                        </b>
                    </p>
                </td>
                <td style="text-align: right;">
                    <div style=" display: inline-block;width: 120px ; padding-top: 10px; padding-right: 200px;">
                        <img src="${path}/image/transsion.png" alt="" style="vertical-align: top">
                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
