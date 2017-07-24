package nichat.com.dummynews;

/**
 * Created by NICHAT on 07-05-2017.
 */

public class NewsItem {
        String mAuthor;
        String mTitle;
        String mDescription;
        String mUrl;
        String mImageUrl;
        String mPublishedAt;

    public NewsItem(String mAuthor, String mTitle, String mDescription, String mUrl, String mImageUrl, String mPublishedAt) {
        this.mAuthor = mAuthor;
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mUrl = mUrl;
        this.mImageUrl = mImageUrl;
        this.mPublishedAt = mPublishedAt;
    }

    public String getAuthor() { return mAuthor;   }

    public String getTitle() {  return mTitle; }

    public String getDescription() { return mDescription;  }

    public String getUrl() { return mUrl; }

    public String getImage() { return mImageUrl;  }

    public String getPublishedAt(){return mPublishedAt;}
}

