from fastapi import FastAPI

import model.verification_request
import model.verification_response
import service.response_constructor as verification

app = FastAPI(
    title='Verification API'
)

APP_CONTEXT = 'verification/api/v1'


@app.post(f"/{APP_CONTEXT}", response_model=model.verification_response.ResponseData)
async def verify(input_data: model.verification_request.VerificationData):
    return verification.construct_response(input_data)
