/*
 * OriginStamp API Documentation
 * The following documentation describes the API v3 for OriginStamp. OriginStamp is a trusted timestamping service that uses the decentralized blockchain to store anonymous, tamper-proof timestamps for any digital content. OriginStamp allows users to timestamp files, emails, or plain text, and subsequently store the created hashes in the blockchain as well as retrieve and verify timetamps that have been committed to the blockchain.The trusted timestamping service of OriginStamp allows you to generate a hash fingerprint and prove that it was created at a specific point in time. If you are interested in integrating trusted timestamping into your own project, feel free to use our provided API. The following interactive documentation describes the interfaces and supports your integration. With this documentation you are able to try out the different requests and see the responses. For the authorization, add your API key to the Authorization header of your request.<br/><h2>Timestamping Steps</h2><ol><li><strong>Determine Hash: </strong> Calculate the SHA-256 of your record using a cryptographic library.</li><li><strong>Create Timestamp: </strong>Create a timestamp and add meta information to index it, e.g. a comment. You can also request a notification (email or webhook) once the tamper-proof timestamp has been created.</li><li><strong>Archive original file: </strong>Since we have no access to your original data, you should archive it because the timestamp is only valid in combination with the original file.</li><li><strong>Check Timestamp Status: </strong>Since the timestamps are always transmitted to the blockchain network at certain times, i.e. there is a delay, you can check the status of a hash and thus get the timestamp information.</li><li><strong>Get Timestamp Proof: </strong>As soon as the tamper-proof timestamp has been generated, you should archive the proof (Merkle Tree), which we created in our open procedure, together with the original file. With this proof, the existence of the file can be verified independently of OriginStamp. Here you can choose if the raw proof (xml) is sufficient proof or if you want to have a certificate (pdf).</li></ol><br/><h2>Installation Notes</h2><ul><li>Make sure you set the Authorization header correctly using your API key.</li><li>If a Cloudflare error occurs, please set a custom UserAgent header.</li><li>Please have a look at the models below to find out what each field means.</li></ul>
 *
 * OpenAPI spec version: 3.0
 * Contact: mail@originstamp.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.originstamp.api;

import com.originstamp.api.client.ApiCallback;
import com.originstamp.api.client.ApiClient;
import com.originstamp.api.client.ApiException;
import com.originstamp.api.client.ApiResponse;
import com.originstamp.api.client.Configuration;
import com.originstamp.api.client.Pair;
import com.originstamp.api.client.ProgressRequestBody;
import com.originstamp.api.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import com.originstamp.model.DefaultOfTimestampResponse;
import com.originstamp.model.TimestampRequest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimestampApi {
    private ApiClient apiClient;

    public TimestampApi() {
        this(Configuration.getDefaultApiClient());
    }

    public TimestampApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for createTimestamp
     * @param authorization A valid API key is essential for authorization to handle the request. (required)
     * @param timestampRequest DTO for the hash submission. Add all relevant information concerning your hash submission. (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call createTimestampCall(String authorization, TimestampRequest timestampRequest, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = timestampRequest;

        // create path and map variables
        String localVarPath = "/v3/timestamp/create";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (authorization != null)
        localVarHeaderParams.put("Authorization", apiClient.parameterToString(authorization));

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "API Key Authorization" };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call createTimestampValidateBeforeCall(String authorization, TimestampRequest timestampRequest, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'authorization' is set
        if (authorization == null) {
            throw new ApiException("Missing the required parameter 'authorization' when calling createTimestamp(Async)");
        }
        
        // verify the required parameter 'timestampRequest' is set
        if (timestampRequest == null) {
            throw new ApiException("Missing the required parameter 'timestampRequest' when calling createTimestamp(Async)");
        }
        

        com.squareup.okhttp.Call call = createTimestampCall(authorization, timestampRequest, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Submission
     * You can submit your hash with this function. If your api key is valid, your hash is added to batch and is scheduled for timestamping. If the hash already exists, the created flag in the response is set to false and the notification(s) of the current request will be totally ignored. You are also able to submit additional information, such as comment or notification credentials. Once a hash is successfully created for a certain crypto-currency, we can notify your desired target with the timestamp information (POST Request). The webhook is triggered as soon as the tamper-proof timestamp with the selected crypto currency has been created. Additionally, it is possible to include a preprint URL in the hash submission. Before the generation of the timestamp hash you can create a random UUID Version 4 and include https://originstamp.com/u/UUID where UUID is your UUID e.g. in a document you want to timestamp. In the preprint URL field you include your UUID and then it is possible to verify the timestamp within the document (or whatever). 
     * @param authorization A valid API key is essential for authorization to handle the request. (required)
     * @param timestampRequest DTO for the hash submission. Add all relevant information concerning your hash submission. (required)
     * @return DefaultOfTimestampResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public DefaultOfTimestampResponse createTimestamp(String authorization, TimestampRequest timestampRequest) throws ApiException {
        ApiResponse<DefaultOfTimestampResponse> resp = createTimestampWithHttpInfo(authorization, timestampRequest);
        return resp.getData();
    }

    /**
     * Submission
     * You can submit your hash with this function. If your api key is valid, your hash is added to batch and is scheduled for timestamping. If the hash already exists, the created flag in the response is set to false and the notification(s) of the current request will be totally ignored. You are also able to submit additional information, such as comment or notification credentials. Once a hash is successfully created for a certain crypto-currency, we can notify your desired target with the timestamp information (POST Request). The webhook is triggered as soon as the tamper-proof timestamp with the selected crypto currency has been created. Additionally, it is possible to include a preprint URL in the hash submission. Before the generation of the timestamp hash you can create a random UUID Version 4 and include https://originstamp.com/u/UUID where UUID is your UUID e.g. in a document you want to timestamp. In the preprint URL field you include your UUID and then it is possible to verify the timestamp within the document (or whatever). 
     * @param authorization A valid API key is essential for authorization to handle the request. (required)
     * @param timestampRequest DTO for the hash submission. Add all relevant information concerning your hash submission. (required)
     * @return ApiResponse&lt;DefaultOfTimestampResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<DefaultOfTimestampResponse> createTimestampWithHttpInfo(String authorization, TimestampRequest timestampRequest) throws ApiException {
        com.squareup.okhttp.Call call = createTimestampValidateBeforeCall(authorization, timestampRequest, null, null);
        Type localVarReturnType = new TypeToken<DefaultOfTimestampResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Submission (asynchronously)
     * You can submit your hash with this function. If your api key is valid, your hash is added to batch and is scheduled for timestamping. If the hash already exists, the created flag in the response is set to false and the notification(s) of the current request will be totally ignored. You are also able to submit additional information, such as comment or notification credentials. Once a hash is successfully created for a certain crypto-currency, we can notify your desired target with the timestamp information (POST Request). The webhook is triggered as soon as the tamper-proof timestamp with the selected crypto currency has been created. Additionally, it is possible to include a preprint URL in the hash submission. Before the generation of the timestamp hash you can create a random UUID Version 4 and include https://originstamp.com/u/UUID where UUID is your UUID e.g. in a document you want to timestamp. In the preprint URL field you include your UUID and then it is possible to verify the timestamp within the document (or whatever). 
     * @param authorization A valid API key is essential for authorization to handle the request. (required)
     * @param timestampRequest DTO for the hash submission. Add all relevant information concerning your hash submission. (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call createTimestampAsync(String authorization, TimestampRequest timestampRequest, final ApiCallback<DefaultOfTimestampResponse> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = createTimestampValidateBeforeCall(authorization, timestampRequest, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<DefaultOfTimestampResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for getHashStatus
     * @param authorization A valid API key is essential for authorization to handle the request. (required)
     * @param hashString The hash in string representation. (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getHashStatusCall(String authorization, String hashString, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/v3/timestamp/{hash_string}"
            .replaceAll("\\{" + "hash_string" + "\\}", apiClient.escapeString(hashString.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (authorization != null)
        localVarHeaderParams.put("Authorization", apiClient.parameterToString(authorization));

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "API Key Authorization" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getHashStatusValidateBeforeCall(String authorization, String hashString, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'authorization' is set
        if (authorization == null) {
            throw new ApiException("Missing the required parameter 'authorization' when calling getHashStatus(Async)");
        }
        
        // verify the required parameter 'hashString' is set
        if (hashString == null) {
            throw new ApiException("Missing the required parameter 'hashString' when calling getHashStatus(Async)");
        }
        

        com.squareup.okhttp.Call call = getHashStatusCall(authorization, hashString, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Status
     * The request returns information of a certain hash read from the URL parameter. The input parameter is a hash in hex representation. Field \&quot;created\&quot; always set to false.
     * @param authorization A valid API key is essential for authorization to handle the request. (required)
     * @param hashString The hash in string representation. (required)
     * @return DefaultOfTimestampResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public DefaultOfTimestampResponse getHashStatus(String authorization, String hashString) throws ApiException {
        ApiResponse<DefaultOfTimestampResponse> resp = getHashStatusWithHttpInfo(authorization, hashString);
        return resp.getData();
    }

    /**
     * Status
     * The request returns information of a certain hash read from the URL parameter. The input parameter is a hash in hex representation. Field \&quot;created\&quot; always set to false.
     * @param authorization A valid API key is essential for authorization to handle the request. (required)
     * @param hashString The hash in string representation. (required)
     * @return ApiResponse&lt;DefaultOfTimestampResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<DefaultOfTimestampResponse> getHashStatusWithHttpInfo(String authorization, String hashString) throws ApiException {
        com.squareup.okhttp.Call call = getHashStatusValidateBeforeCall(authorization, hashString, null, null);
        Type localVarReturnType = new TypeToken<DefaultOfTimestampResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Status (asynchronously)
     * The request returns information of a certain hash read from the URL parameter. The input parameter is a hash in hex representation. Field \&quot;created\&quot; always set to false.
     * @param authorization A valid API key is essential for authorization to handle the request. (required)
     * @param hashString The hash in string representation. (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getHashStatusAsync(String authorization, String hashString, final ApiCallback<DefaultOfTimestampResponse> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getHashStatusValidateBeforeCall(authorization, hashString, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<DefaultOfTimestampResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for getHashStatusForUrlId
     * @param authorization A valid API key is essential for authorization to handle the request. (required)
     * @param urlId The URL ID in UUID-4 format (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getHashStatusForUrlIdCall(String authorization, String urlId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/v3/timestamp/url/{url_id}"
            .replaceAll("\\{" + "url_id" + "\\}", apiClient.escapeString(urlId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (authorization != null)
        localVarHeaderParams.put("Authorization", apiClient.parameterToString(authorization));

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "API Key Authorization" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getHashStatusForUrlIdValidateBeforeCall(String authorization, String urlId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'authorization' is set
        if (authorization == null) {
            throw new ApiException("Missing the required parameter 'authorization' when calling getHashStatusForUrlId(Async)");
        }
        
        // verify the required parameter 'urlId' is set
        if (urlId == null) {
            throw new ApiException("Missing the required parameter 'urlId' when calling getHashStatusForUrlId(Async)");
        }
        

        com.squareup.okhttp.Call call = getHashStatusForUrlIdCall(authorization, urlId, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Status for URL ID
     * The request returns information of a certain URL ID read from the URL parameter. The input parameter is the corresponding UUID-4. Field \&quot;created\&quot; always set to false.
     * @param authorization A valid API key is essential for authorization to handle the request. (required)
     * @param urlId The URL ID in UUID-4 format (required)
     * @return DefaultOfTimestampResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public DefaultOfTimestampResponse getHashStatusForUrlId(String authorization, String urlId) throws ApiException {
        ApiResponse<DefaultOfTimestampResponse> resp = getHashStatusForUrlIdWithHttpInfo(authorization, urlId);
        return resp.getData();
    }

    /**
     * Status for URL ID
     * The request returns information of a certain URL ID read from the URL parameter. The input parameter is the corresponding UUID-4. Field \&quot;created\&quot; always set to false.
     * @param authorization A valid API key is essential for authorization to handle the request. (required)
     * @param urlId The URL ID in UUID-4 format (required)
     * @return ApiResponse&lt;DefaultOfTimestampResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<DefaultOfTimestampResponse> getHashStatusForUrlIdWithHttpInfo(String authorization, String urlId) throws ApiException {
        com.squareup.okhttp.Call call = getHashStatusForUrlIdValidateBeforeCall(authorization, urlId, null, null);
        Type localVarReturnType = new TypeToken<DefaultOfTimestampResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Status for URL ID (asynchronously)
     * The request returns information of a certain URL ID read from the URL parameter. The input parameter is the corresponding UUID-4. Field \&quot;created\&quot; always set to false.
     * @param authorization A valid API key is essential for authorization to handle the request. (required)
     * @param urlId The URL ID in UUID-4 format (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getHashStatusForUrlIdAsync(String authorization, String urlId, final ApiCallback<DefaultOfTimestampResponse> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getHashStatusForUrlIdValidateBeforeCall(authorization, urlId, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<DefaultOfTimestampResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
