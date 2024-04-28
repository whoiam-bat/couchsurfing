from fastapi import FastAPI
from starlette.middleware.cors import CORSMiddleware

import model.verification_request
import model.verification_response
import service.response_constructor as verification

app = FastAPI(
    title='Verification API'
)

APP_CONTEXT = 'verification/api/v1'

origins = [
    "http://localhost:4200",
]
app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


@app.post(f"/{APP_CONTEXT}", response_model=model.verification_response.ResponseData)
async def verify(input_data: model.verification_request.VerificationData):
    return verification.construct_response(input_data)
