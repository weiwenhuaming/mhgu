package cn.jestar.mhgu.version;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import cn.jestar.db.JsonUtils;

/**
 * 创建更新信息
 * Created by 花京院 on 2019/2/5.
 */
public class VersionUpdateTest {
    @Test
    public void getVersion() throws Exception {
        int version = 220;
        String versionName = "2.2.0";
        updateVersionBean(version, versionName);
        updateVersion(version, versionName);
    }


    public void updateVersionBean(int version, String versionName) throws IOException {
        String fileName = "version.json";
        VersionBean bean = new VersionBean();
        bean.setVersion(version);
        bean.setTitle(versionName);
        StringBuilder builder = new StringBuilder("更新说明:");
        addUpdateMsg(builder);
        bean.setMsg(builder.toString());
        FileWriter writer = new FileWriter(fileName);
        writer.write(JsonUtils.toString(bean));
        writer.close();
    }

    private void addUpdateMsg(StringBuilder builder) {
        String separator = System.lineSeparator();
        builder
                .append(separator)
                .append("1 调整技能翻译")
                .append(separator)
                .append("2 调整怪物名翻译")
                .append(separator)
                .append("3 新增配装功能");
//                .append(separator)
//                .append("4 大剑部分重新翻译完成");
    }

    private void updateVersion(int version, String versionName) throws IOException {
        String fileName = "config.gradle";
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder builder = new StringBuilder();
        String separator = System.lineSeparator();
        String text;
        while ((text = reader.readLine()) != null) {
            if (text.contains("vCode")) {
                text = String.format("vCode=%s", version);
            } else if (text.contains("vName")) {
                text = String.format("vName='%s'", versionName);
            }
            builder.append(text).append(separator);
        }
        reader.close();
        FileWriter writer = new FileWriter(fileName);
        writer.write(builder.toString());
        writer.close();
    }

}
