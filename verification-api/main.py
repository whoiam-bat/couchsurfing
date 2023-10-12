from fastapi import FastAPI

import model.verification_request

app = FastAPI(
    title="Verification API"
)

API_NAME = 'verification/api/v1'


@app.get(f"/{API_NAME}")
async def root():
    return {"message": "Hello World"}


@app.post(f"/{API_NAME}")
async def verify(input_data: model.verification_request.VerificationData):
    return {
        'message': f'Verified for {input_data.fullname}',
        'images': input_data.passport_img
    }


''' 
    TODO:
        1 Request
        2 Response {
            status_message,
            message
        }
        3 Verification service
'''
