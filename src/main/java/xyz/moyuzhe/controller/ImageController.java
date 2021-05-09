package xyz.moyuzhe.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.moyuzhe.exception.XmallUploadException;
import xyz.moyuzhe.utils.BaiduUtils;
import xyz.moyuzhe.utils.QiniuUtil;
import xyz.moyuzhe.utils.ResultUtil;
import xyz.moyuzhe.vo.KindEditorResult;
import xyz.moyuzhe.vo.Result;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class ImageController {

    @Autowired
    BaiduUtils baiduUtils;

    @RequestMapping(value = "/image/imageUpload", method = RequestMethod.POST)
    public Result<Object> uploadFile(@RequestParam("file") MultipartFile files,
                                     HttpServletRequest request) {
        JSONObject result = baiduUtils.checkImg(files);
        String conclusion = (String) result.get("conclusion");
        if ("不合规".equals(conclusion)){
            return new ResultUtil<Object>().setErrorMsg("疑似或存在违规图片！请勿传播非法图片");
        }
        String imagePath = null;
        // 文件保存路径
//        String filePath = request.getSession().getServletContext().getRealPath("/upload")+"\\"
//                + QiniuUtil.renamePic(files.getOriginalFilename());
        String filePath = "D:\\upload\\" + QiniuUtil.renamePic(files.getOriginalFilename());
        System.out.println("图片路径：" + filePath);
        // 转存文件
        try {
            //保存至服务器
            File file = new File((filePath));
            files.transferTo(file);
            //上传七牛云服务器
            imagePath = QiniuUtil.qiniuUpload(filePath);
            if (imagePath.contains("error")) {
                throw new XmallUploadException("上传失败");
            }
            // 路径为文件且不为空则进行删除
            if (file.isFile() && file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultUtil<Object>().setData(imagePath);
    }

    //    @RequestMapping(value = "/kindeditor/imageUpload",method = RequestMethod.POST)
    public KindEditorResult kindeditor(@RequestParam("imgFile") MultipartFile files, HttpServletRequest request) {

        KindEditorResult kindEditorResult = new KindEditorResult();
        // 文件保存路径
//        String filePath = request.getSession().getServletContext().getRealPath("/upload")+"\\"
//                + QiniuUtil.renamePic(files.getOriginalFilename());
        String filePath = "D:\\upload\\" + QiniuUtil.renamePic(files.getOriginalFilename());
        //检查文件
        String message = QiniuUtil.isValidImage(request, files);
        if (!message.equals("valid")) {
            kindEditorResult.setError(1);
            kindEditorResult.setMessage(message);
            return kindEditorResult;
        }
        // 转存文件
        try {
            //保存至服务器
            File file = new File((filePath));
            files.transferTo(file);
            //上传七牛云服务器
            String imagePath = QiniuUtil.qiniuUpload(filePath);
            if (imagePath.contains("error")) {
                kindEditorResult.setError(1);
                kindEditorResult.setMessage("上传失败");
                return kindEditorResult;
            }
            // 路径为文件且不为空则进行删除
            if (file.isFile() && file.exists()) {
                file.delete();
            }
            kindEditorResult.setError(0);
            kindEditorResult.setUrl(imagePath);
            return kindEditorResult;
        } catch (IOException e) {
            e.printStackTrace();
        }
        kindEditorResult.setError(1);
        kindEditorResult.setMessage("上传失败");
        return kindEditorResult;
    }

    @RequestMapping(value = "/tinymce/imageUpload", method = RequestMethod.POST)
    public Result<Object> uploadImage(@RequestParam("img") MultipartFile files) {
        JSONObject result = baiduUtils.checkImg(files);
        String conclusion = (String) result.get("conclusion");
        if ("不合规".equals(conclusion)){
            return new ResultUtil<Object>().setErrorMsg("疑似或存在违规图片！请勿传播非法图片");
        }
        String imagePath = null;
        String filePath = "D:\\upload\\" + QiniuUtil.renamePic(files.getOriginalFilename());
        System.out.println("图片路径：" + filePath);

        // 转存文件
        try {
            //保存至服务器
            File file = new File((filePath));
            files.transferTo(file);
            //上传七牛云服务器
            imagePath = QiniuUtil.qiniuUpload(filePath);
            if (imagePath.contains("error")) {
                throw new XmallUploadException("上传失败");
            }
            // 路径为文件且不为空则进行删除
            if (file.isFile() && file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultUtil<Object>().setData(imagePath);
    }

}
