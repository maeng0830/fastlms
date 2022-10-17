package com.maeng0830.fastlms.util;

import com.maeng0830.fastlms.util.PageUtil;

import java.io.File;
import java.time.LocalDate;
import java.util.UUID;

public class BaseController {

    public String getPaperHtml(long totalCount, long pageSize, long pageIndex, String queryString) {

        PageUtil pageUtil = new PageUtil(totalCount, pageSize, pageIndex, queryString);

        return pageUtil.pager();
    }

    public String[] getNewSaveFile(String baseLocalPath, String baseUrlPath, String originalFileName) {

        LocalDate now = LocalDate.now();

        String[] dirs = { String.format("%s\\%d\\", baseLocalPath, now.getYear())
                , String.format("%s\\%d\\%02d\\", baseLocalPath, now.getYear(), now.getMonthValue())
                , String.format("%s\\%d\\%02d\\%02d\\", baseLocalPath, now.getYear(), now.getMonthValue(), now.getDayOfMonth())};

        String urlDir = String.format("%s\\%d\\%02d\\%02d\\", baseUrlPath, now.getYear(), now.getMonthValue(), now.getDayOfMonth());

        for(String dir : dirs) {
            File file = new File(dir);
            if (!file.isDirectory()) {
                file.mkdir();
            }
        }

        String fileExtension = "";
        if (originalFileName != null) {
            int dotPos = originalFileName.lastIndexOf(".");
            if (dotPos > -1) {
                fileExtension = originalFileName.substring(dotPos + 1);
            }
        }

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String newFileName = String.format("%s%s", dirs[2], uuid);
        String newUrlFileName = String.format("%s%s", urlDir, uuid);
        if (fileExtension.length() > 0) {
            newFileName += "." + fileExtension;
            newUrlFileName += "." + fileExtension;
        }

        return new String[]{newFileName, newUrlFileName};
    }
}
