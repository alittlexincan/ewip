package com.zhxu.model.message;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 包含整个预警信息的基本元素。
 *
 * 预警信息采用XML格式封装成文件，文件命名共使用46位（字节），规则如下：
 * 预警信息发布单位_签发时间_预警事件类型_预警事件级别_预警信息状态.XML
 * 示例：33000041600000_20121025091026_11B01_BLUE_ALERT.XML
 * 表示：浙江省气象局于2012年10月25日9点10分26秒（北京时）发布台风蓝色预警（首次发布）。
 *
 * 预警信息附件文件命名规则下：
 * 预警信息发布单位_签发时间_预警事件类型_预警事件级别_预警信息状态_附件类型_附件编号.后缀
 * （1）附件类型采用1位编码，取值如下：
 *     P：预警信息纸质批文扫描件；
 *     O：其它。
 * （2）附件编号，2位数字，对本预警信息所有上传的附件顺序编号，编号范围00~99。
 * （3）用户上传的附件的原始后缀名。
 *
 * 6.2　[datetime]格式说明
 * 采用[datetime]数据格式进行编码的字段(<sendTime>、<effective>、<onset>、<expires>)遵循以下格式:
 * YYYY-MM-DD HH:MI:SSXzh:zm，时间采用北京时，其中
 * YYYY：年；
 * MM：月；
 * DD：天；
 * HH：小时；
 * MI：分钟；
 * SS：秒；
 * X：表示“+”或者“-”，在UTC时间之前的时区用“+”，在UTC时间之后的时区用“-”；
 * zh：日期时间与UTC时间偏移的小时数；
 * zm：日期时间与UTC时间偏移的分钟数。
 */
@Data
public class Alert {
    /**
     * 预警消息唯一标识
     *
     * 格式：ZZZZZZDDDUUUXX_YYYYMMDDHHMISS
     * ZZZZZZDDDUUUXX：预警信息发布单位ID<senderCode>
     * _：固定字符
     * YYYYMMDDHHMISS：预警信息的录入时间
     * （4位年YYYY，2位月MM，2位日DD，2位时HH，2位分MI，2位秒SS）
     *
     * 可选/必选: 必选
     */
    private String identifier;
    /**
     * 预警信息发布单位名称
     *
     * 使用机构全称（包括地域和机构名称）
     * 参见《国家突发事件预警信息发布系统预警信息发布单位编码规范》
     *
     * 可选/必选: 必选
     */
    private String sender;
    /**
     * 预警信息发布单位编码
     *
     * ZZZZZZDDDUUUXX
     * 参见《国家突发事件预警信息发布系统预警信息发布单位编码规范》
     *
     * 可选/必选: 必选
     */
    private String senderCode;
    /**
     * 预警信息的发布时间
     *
     * 参见6.2节[datetime]格式说明
     * 纸质批文上预警信息的发布时间
     *
     * 可选/必选: 必选
     */
    private Date sendTime;
    /**
     * 预警信息类型
     *
     * Actual（实际）
     *   接收者可采取实际行动的真实预警；
     * Exercise（演练）
     *   只针对演练参与者，具体接收者在<note>中描述；
     * Test（测试）
     *   所有接收者均可忽略的消息；
     * Draft（草稿）
     *   还未正式发布的预警信息。
     * 目前取值仅使用“Actual”和“Test”，
     * 其中 “Test”可用于发布测试预警，Exercise和Draft暂不使用。
     *
     * 可选/必选: 必选
     */
    private String status;
    /**
     * 预警信息状态
     *
     * Alert（首次）
     *   首次发布的预警；
     * Update(更新)
     *   更新预警信息（续发属于更新）；
     *   更新是针对哪条预警信息标识在<reference>字段
     * Cancel(解除)
     *   一次预警事件生命周期结束后，可对其进行解除。
     *    解除的预警信息identifier在<reference>字段描述
     *    解除的原因可在<note>字段描述；
     * Ack（确认）
     *   确认收到的预警信息。
     *    收到的预警信息identifier在<reference>字段描述；
     * Error（错误）
     *   表示拒绝接收到的预警信息。
     *    拒绝的预警信息identifier在<reference>字段描述；
     *    拒绝的原因在<note>字段解释。
     * 目前只采用“Alert”“Update”“Cancel”三个枚举值，其余枚举值保留，暂不使用。
     *
     * 可选/必选: 必选
     */
    private String msgType;
    /**
     * 预警信息的发布范围
     *
     * Public（公开）
     *   针对不受限制的对象
     * Restricted（限制权限）
     * Private（特定地址）
     *
     * 固定使用“Public”值，其余两个枚举值保留，暂不使用。
     *
     * 可选/必选: 必选
     */
    private String scope;
    /**
     * 预警信息的发布手段和发布对象
     *
     * 如下格式定义：
     * <code>
     *   <method>
     *     <methodName>发布手段编码</methodName>
     *     <message>
     *         使用该手段发布的发布内容（若为空，则发布手段到<description>字段取完整的预警信息内容）
     *     </message>
     *     <audienceGrp>
     *       使用该手段发布的对象/对象群组ID
     *     </audenceGrp>
     *     <audenceprt>
     *       使用该手段发布的单独对象
     *     </audiencePrt>
     *   </method>
     * </code>
     *
     * 发布手段编码（可在以下枚举值基础上扩展）
     *
     * SMS（短信）表示通过手机短信发布；
     * LED（电子显示屏）表示通过电子显示屏发布；
     * SPEAKER（大喇叭）表示通过大喇叭发布；
     * TV（电视台）表示通过电视台发布；
     * BROADCAST广播电台 表示通过广播电台发布；
     * WEB（网站）表示通过网站发布
     * PHONE（电话）表示通过电话发布
     * FAX（传真）表示通过传真发布
     *
     * 允许在code中定义多个<method>段，描述多种发布手段
     *
     * 可选/必选: 必选
     */
    private String code;

    /**
     * 密级
     *
     * 取值包括：
     * None（无）
     * Inner（内部）
     * 默认为“None”
     *
     * 可选/必选: 可选
     */
    private String secClassification;
    /**
     * 对预警信息解除原因的说明
     *
     * 若<msgType> 为“Cancel”，则在此说明其原因。
     *
     * 可选/必选: 可选
     */
    private String note;
    /**
     * 描述本条预警信息引用的预警信息
     *
     * <msgType>为“Update”时，本字段表示所更新的预警事件的首发信息<identifier>；
     * <msgType>为“Cancel”时，本字段表示所解除的预警事件的首发信息<identifier>；
     *
     * 可选/必选: 可选
     */
    private String references;

    private List<Info> infos;

    /**
     * 包含“预警信息”主题的所有子元素
     */
    @Data
    public class Info {
        /**
         * 预警事件类型编码
         *
         * 取值见《国家应急平台体系信息资源分类与编码规范》
         *
         * 可选/必选: 必选
         */
        private String eventType;
        /**
         * 预警事件的紧急程度
         *
         * 取值包括下下5种：
         * Immediate(立即行动)
         * Expected(准备行动)
         * Future(等待行动)
         * Past(已过去)
         * Unknown(未知)
         *
         * 默认"Unknown"，其它枚举值保留，暂不使用
         *
         * 可选/必选: 必选
         */
        private String urgency;
        /**
         * 预警事件的严重程度（预警级别）
         *
         * 取值包括下下5种：
         * Red(红色预警/I级/特别重大)
         * Orange(橙色预警/II级/重大)
         * Yellow(黄色预警/III/较大)
         * Blue(蓝色预警/IV/一般)
         * Unknown(未知,9)
         * （注：界面上的级别列表显示如“红色预警/I级/特别重大”）
         *
         * 可选/必选: 必选
         */
        private String severity;
        /**
         * 预警事件发生的可能程度
         *
         * 取值包括下：
         * Observed(确定发生)
         * VeryLikely(非常可能)
         * Likely(可能发生)
         * Unlikely(不大可能)
         * Unknown(未知) 可信度未知。
         * 默认Unknown，其它枚举值保留，暂不使用。
         *
         * 可选/必选: 必选
         */
        private String certainty;
        /**
         * 预警信息生效时间
         *
         * 格式参见6.2节[datetime]格式说明
         * 预警信息在系统中的签发时间
         *
         * 可选/必选: 必选
         */
        private Date effective;
        /**
         * 系统中签发本条预警信息的发布单位签发员
         *
         * 可选/必选: 必选
         */
        private String senderName;
        /**
         * 预警信息的标题
         *
         * 当<msgType>字段值为Alert或Update时，<Headline>字段格式为：
         * <sender>（中文）+“发布”+<eventType>（中文）+<severity>（中文）
         * 例如：浙江省气象局发布台风黄色预警
         * 当<msgType>字段值为Cancel时，<Headline>字段格式为：
         * <sender>（中文）+“解除”+<eventType>（中文）+<severity>（中文）
         * 例如：浙江省气象局解除台风黄色预警
         * （标题中级别只显示颜色，界面上下拉框选择时显示红色预警（I级/特别重大）
         *
         * 可选/必选: 必选
         */
        private String Headline;
        /**
         * 预警信息正文
         *
         * 可选/必选: 必选
         */
        private String description;

        /**
         * 预警信息描述所使用的语言代码
         *
         * 取值参照RFC3066 Natural language identifier
         * 默认值为“zh-CN”
         *
         * 可选/必选: 可选
         */
        private String language;
        /**
         * 预警事件的预期发生时间
         *
         * 可选/必选: 可选
         */
        private String Onset;
        /**
         * 预警事件的失效时间
         *
         * 可选/必选: 可选
         */
        private String Expires;
        /**
         * 对建议采取措施的描述。
         *
         * 可选/必选: 可选
         */
        private String instruction;
        /**
         * 预警信息内容中包含的网址信息，
         * 提醒受众可通过网址查看预警详细信息
         *
         * 可选/必选: 可选
         */
        private String web;

        private List<Resource> resources;
        private List<Area> areas;
    }

    /**
     * 包含附件的子元素
     */
    @Data
    public class Resource {
        /**
         * 附件文件名
         *
         * 系统根据用户上传的附件自动生成的与预警信息文件名匹配的附件文件名，
         * 参见第8节预警信息附件文件命名。
         *
         * 可选/必选: 必选
         */
        private String resourceDesc;
        /**
         * 附件的文件大小
         *
         * 单位为byte。
         * 由系统自动生成。
         *
         * 可选/必选: 必选
         */
        private Integer size;

        /**
         * 附件内容的Hash码
         *
         * 采用SHA-1算法，参照FIPS 180-2。
         * 用于判断附件是否被篡改。
         * SHA-1规范URL：http://en.wikipedia.org/wiki/SHA-1。
         * 默认为空。
         *
         * 可选/必选: 可选
         */
        private String digest;
    }

    /**
     * 包含影响区域和发布区域的子元素
     */
    @Data
    public class Area {
        /**
         * 对突发事件影响区域的文字描述
         *
         * 可选/必选: 必选
         */
        private String areaDesc;
        /**
         * 预警信息发布区域的行政区划代码
         *
         * 用行政区划代码表示预警信息的发布区域，
         * 多个行政区划代码之间用“，”分隔，
         * 行政区划代码参见《国家突发事件预警信息发布系统预警信息地域编码规范》
         *
         * 可选/必选: 必选
         */
        private String geocode;

        /**
         * 用多边形表示预警信息的发布区域
         *
         * 值为多个坐标点对，用空格分隔；
         * 描述多边形时。首尾为同一个点，为封闭图形；
         * 描述线状元素时，首尾不封闭。
         *
         * 可选/必选: 可选
         */
        private String polygon;
        /**
         * 用圆形表示预警信息的发布区域，以圆心点和半径表示
         *
         * 值为坐标点对和半径，用空格分隔；
         * 点状元素，可用半径为0的circle描述；
         * 半径单位为米。
         *
         * 可选/必选: 可选
         */
        private String circle;
    }
}
