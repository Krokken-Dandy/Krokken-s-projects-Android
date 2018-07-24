package com.example.krokken.newsreport;

public class NewsReport {

    private String mNewsTitle;

    private String mNewsReportWebsite;

    private String mPostedTime;

    private String mSectionName;

    private String mContributorName;

    private String mLastName;

    public NewsReport(String title, String newsUrl, String postedDate, String sectionName,
                      String contributorName){
        mNewsTitle = title;
        mPostedTime = postedDate;
        mNewsReportWebsite = newsUrl;
        mSectionName = sectionName;
        mContributorName = contributorName;
    }

    public String getNewsTitle() {
        return mNewsTitle;
    }

    public String getTime() {
        return mPostedTime;
    }

    public String getNewsReportWebsite() {
        return mNewsReportWebsite;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public String getContributorName() {
        return mContributorName;
    }

}
