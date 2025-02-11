/*
 * Copyright (c) 2022, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.identity.rest.api.user.backupcode.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.wso2.carbon.identity.rest.api.user.backupcode.v1.dto.*;
import org.wso2.carbon.identity.rest.api.user.backupcode.v1.MeApiService;
import org.wso2.carbon.identity.rest.api.user.backupcode.v1.factories.MeApiServiceFactory;

import io.swagger.annotations.ApiParam;

import org.wso2.carbon.identity.rest.api.user.backupcode.v1.dto.ErrorDTO;
import org.wso2.carbon.identity.rest.api.user.backupcode.v1.dto.BackupCodeResponseDTO;
import org.wso2.carbon.identity.rest.api.user.backupcode.v1.dto.UserRequestDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import javax.validation.Valid;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/me")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(value = "/me", description = "the me API")
public class MeApi  {

    @Autowired
    private MeApiService delegate;

    @Valid
    @DELETE
    @Path("/backup-code")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Delete Backup codes of the authenticated user.",
            notes = "This API is used to delete backup codes of the authenticated user.\n\n<b>Permission required:</b><br> * none<br> <b>Scope required:</b><br> * internal_login\n",
            response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Backup codes deleted successfully"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response meBackupCodeDelete() {

        return delegate.meBackupCodeDelete();
    }

    @Valid
    @GET
    @Path("/backup-code")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Retrieve backup codes of the authenticated user.",
            notes = "This API is used to retrieve backup codes of the authenticated user.\n\n\n<b>Permission required:</b>\n    * none\n<b>Scope required:</b>\n    * internal_login\n",
            response = BackupCodeResponseDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response meBackupCodeGet() {

        return delegate.meBackupCodeGet();
    }

    @Valid
    @POST
    @Path("/backup-code")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Init, Refresh the backup codes\n",
            notes = "This API is used to perform following actions.\n* <b>INIT</b>- Generate Backup Codes for the authenticated user.\n* <b>REFRESH</b> - Refresh Backup Codes of the authenticated user.\n\n<b>Permission required:</b>\n    * none\n<b>Scope required:</b>\n    * internal_login\n",
            response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Backup codes successfully refreshed"),
        
        @io.swagger.annotations.ApiResponse(code = 201, message = "Created"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response meBackupCodePost(@ApiParam(value = "Actions supported by the API. Actions can be INIT or REFRESH." ,required=true ) @Valid UserRequestDTO request) {

        return delegate.meBackupCodePost(request);
    }

}
