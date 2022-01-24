package com.esmooc.legion.edu.common.utils;

import com.esmooc.legion.edu.common.constant.Constants;
import com.esmooc.legion.edu.entity.vo.FileVO;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FileUtils
 * @Author sun
 * @Date 2020-12-23 14:52
 **/
public class FileUtils {

    private final static String path = Constants.FILE_PATH;

    static {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();//创建目录
        }
    }

    /**
     * 多文件写入服务器
     *
     * @param files
     * @return
     */
    public static List<FileVO> writeFile(MultipartFile[] files) {
        List<FileVO> list = new ArrayList<FileVO>();
        for (MultipartFile multipartFile :
                files) {
            list.add(writeFile(multipartFile));
        }
        return list;
    }

    /**
     * 单文件写入服务器
     *
     * @param file
     * @return
     */
    public static FileVO writeFile(MultipartFile file) {
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        FileVO vo = new FileVO();
        String filesName = file.getOriginalFilename();
        String suffixName = filesName.substring(filesName.lastIndexOf(".") + 1);
        String UUIDName = BaseUtils.getUUID();
        String saveName = UUIDName + "-" + filesName;
        String filePath = path + "/" + saveName;
        try {
            fileOutputStream = new FileOutputStream(filePath);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(file.getBytes());
            vo.setFileName(saveName);
            vo.setName(filesName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedOutputStream.flush();
                fileOutputStream.close();
                bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return vo;
    }


    /**
     * 获得文件字节流
     *
     * @param fileName 文件名称
     * @return 字节流
     */
    public static byte[] readFile(String fileName) {
        String filePath = path + "/" + fileName;
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        try {
            fileInputStream = new FileInputStream(filePath);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            int bytesRead = 0;
            while (-1 != (bytesRead = bufferedInputStream.read(buff, 0, buff.length))) {
                bao.write(buff, 0, bytesRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                bufferedInputStream.close();
                buff = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bao.toByteArray();
    }

    /**
     * 删除服务器多文件
     *
     * @param fileNames 文件名称
     * @return
     */
    public static void deleteFiles(List<String> fileNames) {
        for (String fileName :
                fileNames) {
            String filePath = path + "/" + fileName;
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * 删除服务器单文件
     *
     * @param fileName 文件名称
     * @return
     */
    public static void deleteFile(String fileName) {
        String filePath = path + "/" + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            file.delete();
        }
    }

    /**
     * 文件版本号
     *
     * @param versions 最新版本号
     * @return 最新版本号
     */
    public static String getVersions(String versions) {
        String str = "";
        if (versions != null && !"".equals(versions)) {
            String[] strs = versions.split("V");
            String hou = strs[1].substring(strs[1].lastIndexOf(".") + 1);
            String qian = strs[1].substring(0, 1);
            int xiaobanben = Integer.parseInt(hou);
            int dabanben = Integer.parseInt(qian);
            if (xiaobanben < 99) {
                xiaobanben = xiaobanben + 1;
            } else {
                xiaobanben = 1;
                dabanben = dabanben + 1;
            }
            if (xiaobanben <= 9) {
                str = "V" + dabanben + ".0" + xiaobanben;
            } else {
                str = "V" + dabanben + "." + xiaobanben;
            }
        } else {
            str = "V1.01";
        }
        return str;
    }

    /**
     * 文件复制
     */
    public static FileVO copyFile(String changName, String originalName) {
        FileVO vo = new FileVO();
        String UUIDName = BaseUtils.getUUID();
        String saveName = UUIDName + "-" + changName;
        //目标文件
        String url1 = path + "/" + originalName;
        //要赋值的路径及文件名
        String url2 = path + "/" + saveName;
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(new File(url1));
            out = new FileOutputStream(new File(url2));
        } catch (FileNotFoundException e) {
            return null;
        }
        byte[] buff = new byte[10240];
        int n = 0;
        try {
            while ((n = in.read(buff)) != -1) {
                out.write(buff, 0, n);
            }
            vo.setFileName(saveName);
            vo.setName(changName);
            out.flush();
            in.close();
            out.close();
        } catch (IOException e) {
            return null;
        }
        return vo;
    }

    /**
     * 将本地文件转Base64
     *
     * @param fileName
     * @return
     */
    public static String fileToBase64(String fileName) {
        String filePath = path + "/" + fileName;
        File file = new File(filePath);
        long size = file.length();
        byte[] bytes = new byte[(int) size];
        FileInputStream fs = null;
        BufferedInputStream bis = null;
        try {
            fs = new FileInputStream(file);
            bis = new BufferedInputStream(fs);
            bis.read(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Base64.encodeBase64String(bytes);
    }

    /**
     * 下载文件名重新编码
     *
     * @param request  请求对象
     * @param fileName 文件名
     * @return 编码后的文件名
     */
    public static String setFileDownloadHeader(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
        final String agent = request.getHeader("USER-AGENT");
        String filename = fileName;
        if (agent.contains("MSIE")) {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            filename = new String(fileName.getBytes(), "ISO8859-1");
        } else if (agent.contains("Chrome")) {
            // google浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        } else {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }

    /**
     * 下载文件名重新编码
     *
     * @param response     响应对象
     * @param realFileName 真实文件名
     * @return
     */
    public static void setAttachmentResponseHeader(HttpServletResponse response, String realFileName) throws UnsupportedEncodingException {
        String percentEncodedFileName = percentEncode(realFileName);

        StringBuilder contentDispositionValue = new StringBuilder();
        contentDispositionValue.append("attachment; filename=")
                .append(percentEncodedFileName)
                .append(";")
                .append("filename*=")
                .append("utf-8''")
                .append(percentEncodedFileName);

        response.setHeader("Content-disposition", contentDispositionValue.toString());
    }

    /**
     * 百分号编码工具方法
     *
     * @param s 需要百分号编码的字符串
     * @return 百分号编码后的字符串
     */
    public static String percentEncode(String s) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode(s, StandardCharsets.UTF_8.toString());
        return encode.replaceAll("\\+", "%20");
    }


    /**
     * 输出指定文件的byte数组
     *
     * @param filePath 文件路径
     * @param os       输出流
     * @return
     */
    public static void writeBytes(String filePath, OutputStream os) throws IOException {
        FileInputStream fis = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
