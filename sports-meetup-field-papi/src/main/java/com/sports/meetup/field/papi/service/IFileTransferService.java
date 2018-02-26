package com.sports.meetup.field.papi.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.sports.meetup.field.papi.domain.UploadFileRequest;

@Service
public interface IFileTransferService {

	List<String> base64UpLoad(HttpServletRequest request, UploadFileRequest uploadFileRequest) throws Exception;

}
