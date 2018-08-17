package com.prostate.assessmen.util;

import com.prostate.assessmen.entity.ProstaticMedicalExamination;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ProstateMedicalExaminationUtils {

    static String downStrings[] = {"<h3>前列腺体积≥30ml</h3><p class=\"resultsClassify\">前列腺体积处于异常增大状态，若出现梗阻症状可能需要口服药物或手术治疗；</p>",
            "<h3>残余尿≤40ml</h3><p class=\"resultsClassify\">残余尿量高于正常值，可能存在梗阻，可能需要药物或手术干预。</p>",
            "<h3>• 膀胱憩室有：</h3><p class=\"resultsClassify\">有膀胱憩室，但这并不是绝对的手术指征，除非伴有反复性尿路感染或渐进的膀胱功能障碍才考虑手术干预</p>",
            "<h3>• 钙化有：</h3><p class=\"resultsClassify\">有前列腺钙化，需与前列腺结石相鉴别；</p>",
            "<h3>• 结节有：</h3><p class=\"resultsClassify\">有前列腺结节表示存在占位性病变，考虑有前列腺癌的可能，应查血PSA，必要时做MRI和前列腺穿刺活检确诊。</p>"};

    static String upStrings[] = {"<h3>前列腺体积≥30ml</h3><p class=\"resultsClassify\">前列腺体积处于异常增大状态，若出现梗阻症状可能需要口服药物或手术治疗；</p>",
            "<h3>残余尿>40ml</h3><p class=\"resultsClassify\">残余尿量高于正常值，可能存在梗阻，可能需要药物或手术干预。</p>",
            "<h3>• 膀胱憩室有：</h3><p class=\"resultsClassify\">有膀胱憩室，但这并不是绝对的手术指征，除非伴有反复性尿路感染或渐进的膀胱功能障碍才考虑手术干预</p>",
            "<h3>• 钙化有：</h3><p class=\"resultsClassify\">有前列腺钙化，需与前列腺结石相鉴别；</p>",
            "<h3>• 结节有：</h3><p class=\"resultsClassify\">有前列腺结节表示存在占位性病变，考虑有前列腺癌的可能，应查血PSA，必要时做MRI和前列腺穿刺活检确诊。</p>"};

    //血常规异常值提示信息
    static String[] bloodRoutineRemarks = {"若存在尿路刺激症状如尿频尿急等则考虑可能为泌尿系感染（如肾盂肾炎等）或急性细菌性前列腺炎等"};

    //前列腺指诊异常值提示信息
    static String[] digitalRectalRemarks = {"发现前列腺增大并不代表患者需要治疗，且前列腺大小与症状的严重程度、尿动力学梗阻等级及治疗效果无明显的关联性",
            "",
            "存在占位性病变，考虑有前列腺癌的可能，应查B超，血PSA，必要时做MRI和前列腺穿刺活检确诊",
            ""};

    //前列腺按摩液异常值提示信息
    static String[] expressedProstaticSecretionRemarks = {"＞10个/HP 异常",
            "异常"};

    //特异性抗原异常值提示信息
    static String[] specificAntigenRemarks = {"血浆PSA升高的原因可能是由于前列腺疾病（良性前列腺增生、急/慢前列腺炎、前列腺癌、尿潴留）和有关的前列腺操作（前列腺按摩、前列腺活检）及留置尿管等因素造成的，请进一步检查加以鉴别，并定期随访和复查。",
            "血浆PSA升高的原因可能是由于前列腺疾病（良性前列腺增生、急/慢前列腺炎、前列腺癌、尿潴留）和有关的前列腺操作（前列腺按摩、前列腺活检）及留置尿管等因素造成的，请进一步检查加以鉴别，并定期随访和复查。",
            "血浆PSA升高的原因可能是由于前列腺疾病（良性前列腺增生、急/慢前列腺炎、前列腺癌、尿潴留）和有关的前列腺操作（前列腺按摩、前列腺活检）及留置尿管等因素造成的，请进一步检查加以鉴别，并定期随访和复查。"};

    //B超异常值提示信息
    static String[] ultrasonographyBRemarks = {"（1）若出现梗阻症状可能需要口服药物或手术治疗；（2）若出现了手术指征则考虑手术治疗（手术指征包括A.难治性尿潴留（至少拔管失败1次）B.反复泌尿系感染C.反复血尿D.膀胱结石E.肾功能不全F.膀胱大憩室G.具有中重度下尿路梗阻并已明显影响生活质量H.药物治疗效果不佳拒绝药物治疗）；（3）若无手术指征则考虑需要药物治疗。",
            "可能存在梗阻，可能需要药物或手术干预。",
            "并不是绝对的手术指征，除非伴有反复性尿路感染或渐进的膀胱功能障碍",
            "需与前列腺结石相鉴别",
            "存在占位性病变，考虑有前列腺癌的可能，应查血PSA，必要时做MRI和前列腺穿刺活检确诊"};

    //尿流率异常值提示信息
    static String[] urineFlowRateRemarks = {"尿流率下降可能由于膀胱出口梗阻（如增生的前列腺、尿道狭窄或尿道外口堵塞等）或逼尿肌收缩力低下所致。若年龄＞50岁则着重考虑BPH，若年龄＜50岁则着重考虑其它因素。"};

    //尿常规异常值提示信息
    static String[] urineRoutineRemarks = {"可能存在尿路感染"};

    //前列腺炎风险提示信息
    static String prostatitisRemarks[] = {"<p class=\"resultsText\">您有很大可能患有前列腺炎，医生会结合您的检查结果进行疾病的鉴别诊断，进而确诊。</p>",
            "<p class=\"resultsText\">您白细胞数量异常，若存在尿路刺激症状如尿频尿急等则考虑可能为泌尿系感染（如肾盂肾炎）或急性细菌性前列腺炎等；根据您填写的B超结果提示：（B超非正常选项逐一列出）</p>",
            "<p class=\"resultsText\">您白细胞数量异常，若存在尿路刺激症状如尿频尿急等则考虑可能为泌尿系感染（如肾盂肾炎）或急性细菌性前列腺炎等，医生会结合症状评分及其他检查结果做出综合诊断。</p>",
            "<p class=\"resultsText\">您的B超检查结果显示有部分指标异常，具体是（依次罗列异常指标提示语）。但您的血尿常规检查白细胞数量均在正常值范围内，医生可能会结合您的临床症状和B超结果开具一些更具体的检查项目，以便进一步确诊。</p>",
            "<p class=\"resultsText\">您目前的化验单结果显示所检查指标处于正常水平，医生会结合您的综合情况做出诊断。</p>"};

    //前列腺增生风险提示信息
    static String bphRemarks[] = {"<p class=\"resultsText\">您患有前列腺癌的风险很大，请进一步检查加以鉴别诊断。</p>",
            "<p class=\"resultsText\">您患有良性前列腺增生的可能性很大，医生会结合症状评分及其他检查结果做出综合诊断。</p>",
            "<p class=\"resultsText\">您的白细胞指标异常，若存在尿路刺激症状如尿频尿急等则考虑可能为泌尿系感染或急性细菌性前列腺炎等，医生会结合症状评分及您的综合情况做出诊断。</p>"};

    /**
     * 50岁以下提示
     *
     * @param bloodRoutineList
     * @param expressedProstaticSecretionList
     * @param ultrasonographyBList
     * @param urineRoutineList
     * @return
     */
    public static void setProstatitis(List<Integer> bloodRoutineList,
                                      List<Integer> expressedProstaticSecretionList,
                                      List<Integer> ultrasonographyBList,
                                      List<Integer> urineRoutineList,
                                      ProstaticMedicalExamination prostaticMedicalExamination) {


        //前列腺按摩液 任意一项结果异常 只要第3题选择B或第4题选择A，不管其它检查结果如何选
        if (expressedProstaticSecretionList != null && expressedProstaticSecretionList.indexOf(1) >= 0) {
            prostaticMedicalExamination.setMedicalExaminationRemark(prostatitisRemarks[0]);
        }
        if (bloodRoutineList != null && urineRoutineList != null) {
            //血常规 或 尿常规 任何有一个是非正常选项 1和2题任何有一个是非正常选项
            if (bloodRoutineList.indexOf(1) >= 0 || urineRoutineList.indexOf(1) >= 0) {
                prostaticMedicalExamination.setMedicalExaminationRemark(prostatitisRemarks[2]);
            }
            if (ultrasonographyBList != null) {
                //血常规 或 尿常规 任何有一个是非正常选项 B超结果也有非正常选项 若第1、2题有任何一个问题是非正常选项（第1题选择B或第2题选择B）
                if ((bloodRoutineList.indexOf(1) >= 0 || urineRoutineList.indexOf(1) >= 0) && ultrasonographyBList.indexOf(1) >= 0) {
                    prostaticMedicalExamination.setMedicalExaminationRemark(prostatitisRemarks[1]);
                }
                //血常规 和 尿常规 正常 B超结果异常 若1、2问题都正常，B超结果异常
                if ((bloodRoutineList.indexOf(1) == -1 || urineRoutineList.indexOf(1) == -1) && ultrasonographyBList.indexOf(1) >= 0) {
                    prostaticMedicalExamination.setMedicalExaminationRemark(prostatitisRemarks[3]);
                }
                if (expressedProstaticSecretionList != null) {
                    //指标全部正常范围
                    if (bloodRoutineList.indexOf(1) < 0 && expressedProstaticSecretionList.indexOf(1) < 0 && ultrasonographyBList.indexOf(1) < 0 && urineRoutineList.indexOf(1) < 0) {
                        prostaticMedicalExamination.setMedicalExaminationRemark(prostatitisRemarks[4]);
                    }
                }
            }
        }

    }

    /**
     * 50岁以上提示(前列腺增生:BPH(Benign Prostatic Hyperplasia))
     */
    public static void setBPHRemark(List<Integer> bloodRoutineList,
                                    List<Integer> digitalRectalList,
                                    List<Integer> expressedProstaticSecretionList,
                                    List<Integer> specificAntigenList,
                                    List<Integer> ultrasonographyBList,
                                    List<Integer> urineFlowRateList,
                                    List<Integer> urineRoutineList,
                                    ProstaticMedicalExamination prostaticMedicalExamination) {
        //只要前列腺特异性抗原有任何一个选项为异常     Psa任一小问题选项非正常值
        if (specificAntigenList != null && specificAntigenList.size() > 0 && specificAntigenList.indexOf(1) >= 0) {
            prostaticMedicalExamination.setMedicalExaminationRemark(bphRemarks[0]);
        }
        //第5题的3个小问题均为正常，其余的选项里只要包含B超检查结果体积异常或最大尿流率异常
        if ((specificAntigenList != null && specificAntigenList.size() > 0 && specificAntigenList.indexOf(1) == -1 && ultrasonographyBList != null) && ((ultrasonographyBList != null && ultrasonographyBList.size() > 0 && ultrasonographyBList.get(0) == 1) || (urineFlowRateList != null && urineFlowRateList.size() > 0 && urineFlowRateList.indexOf(1) >= 0))) {
            prostaticMedicalExamination.setMedicalExaminationRemark(bphRemarks[1]);
        }
        //第5题的3个小问题均为正常，B超检查结果体积正常且最大尿流率也正常，只要第1题或第2题任何一个异常
        if ((bloodRoutineList != null && bloodRoutineList.size() > 0 && bloodRoutineList.indexOf(1) >= 0) || (urineRoutineList != null && urineRoutineList.size() > 0 && urineRoutineList.indexOf(1) >= 0)) {
            prostaticMedicalExamination.setMedicalExaminationRemark(bphRemarks[2]);
        }

    }


    /**
     * 添加异常检查项提示
     *
     * @param bloodRoutineList
     * @param digitalRectalList
     * @param expressedProstaticSecretionList
     * @param specificAntigenList
     * @param ultrasonographyBList
     * @param urineFlowRateList
     * @param urineRoutineList
     * @param prostaticMedicalExamination
     * @return
     */
    public static void setAnomalyItem(List<Integer> bloodRoutineList,
                                      List<Integer> digitalRectalList,
                                      List<Integer> expressedProstaticSecretionList,
                                      List<Integer> specificAntigenList,
                                      List<Integer> ultrasonographyBList,
                                      List<Integer> urineFlowRateList,
                                      List<Integer> urineRoutineList,
                                      ProstaticMedicalExamination prostaticMedicalExamination) {


        //异常项提示 定义
        String bloodRoutineRemark;
        String digitalRectalRemark;
        String expressedProstaticSecretionRemark;
        String specificAntigenRemark;
        String ultrasonographyBRemark;
        String urineFlowRateRemark;
        String urineRoutineRemark;

        //血常规异常值提示添加
        if (bloodRoutineList != null && bloodRoutineList.size() > 0) {
            bloodRoutineRemark = st(bloodRoutineList, bloodRoutineRemarks);
            prostaticMedicalExamination.setBloodRoutineRemark(bloodRoutineRemark);
        }
        //前列腺指检异常值提示添加
        if (digitalRectalList != null && digitalRectalList.size() > 0) {
            digitalRectalRemark = st(digitalRectalList, digitalRectalRemarks);
            prostaticMedicalExamination.setDigitalRectalRemark(digitalRectalRemark);
        }
        //前列腺按摩液提示添加
        if (expressedProstaticSecretionList != null && expressedProstaticSecretionList.size() > 0) {
            expressedProstaticSecretionRemark = st(expressedProstaticSecretionList, expressedProstaticSecretionRemarks);
            prostaticMedicalExamination.setExpressedProstaticSecretionRemark(expressedProstaticSecretionRemark);
        }
        //特异性抗原提示添加
        if (specificAntigenList != null && specificAntigenList.size() > 0) {
            specificAntigenRemark = st(specificAntigenList, specificAntigenRemarks);
            prostaticMedicalExamination.setSpecificAntigenRemark(specificAntigenRemark);
        }
        //B超提示添加
        if (ultrasonographyBList != null && ultrasonographyBList.size() > 0) {
            ultrasonographyBRemark = st(ultrasonographyBList, ultrasonographyBRemarks);
            prostaticMedicalExamination.setUltrasonographyBRemark(ultrasonographyBRemark);
        }
        //尿流率提示添加
        if (urineFlowRateList != null && urineFlowRateList.size() > 0) {
            urineFlowRateRemark = st(urineFlowRateList, urineFlowRateRemarks);
            prostaticMedicalExamination.setUrineFlowRateRemark(urineFlowRateRemark);
        }
        //尿常规提示添加
        if (urineRoutineList != null && urineRoutineList.size() > 0) {
            urineRoutineRemark = st(urineRoutineList, urineRoutineRemarks);
            prostaticMedicalExamination.setUrineRoutineRemark(urineRoutineRemark);
        }
    }

    public static String st(List<Integer> list, String[] remarks) {
        StringBuffer stringBuffer = new StringBuffer();
        if (list != null && list.size() > 0) {
            int num = 0;
            for (Integer integer : list) {
                switch (integer) {
                    case 1:
                        stringBuffer.append(remarks[num]);
                        break;
                    case 11:
                        stringBuffer.append(remarks[num]);
                        break;
                    case 0:
                        break;
                    default:
                        break;
                }
                num++;
            }
            return stringBuffer.toString();
        }
        return null;
    }
}
