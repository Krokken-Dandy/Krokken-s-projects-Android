package com.example.krokken.newsreport;

public class NewsReport {

    // Variable for the title of each news article
    private String mNewsTitle;

    // Variable for the URL of the news article, used for the intent
    private String mNewsReportWebsite;

    // Variable for the time the article was posted
    private String mPostedTime;

    // Variable for the section type of the news article
    private String mSectionName;

    // Variable for the name of the contributor
    private String mContributorName;

    public NewsReport(String title, String newsUrl, String postedDate, String sectionName,
                      String contributorName) {
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
