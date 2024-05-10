from pydantic import BaseModel, Field


class VerificationData(BaseModel):
    fullname: str = Field(min_length=2),
    passport_img: str
