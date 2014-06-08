package tqkey.android.handlers

import com.loopj.android.http.AsyncHttpResponseHandler
import tqkey.android.events.FailureCallApiEvent
import tqkey.android.events.SuccessCallApiEvent

public class ApiResponseHandler extends AsyncHttpResponseHandler {

    private ApiResponseHandlerDelegate delegate

    public ApiResponseHandler(ApiResponseHandlerDelegate delegate) {
        this.delegate = delegate
    }

    @Override
    public void onSuccess(String content) {
        delegate.onSuccessCallApi(new SuccessCallApiEvent(content))
    }

    @Override
    public void onFailure(int statusCode, Throwable error, String content) {
        delegate.onFailureCallApi(new FailureCallApiEvent(statusCode))
    }

    public interface ApiResponseHandlerDelegate {
        void onSuccessCallApi(SuccessCallApiEvent event)

        void onFailureCallApi(FailureCallApiEvent event)
    }

}