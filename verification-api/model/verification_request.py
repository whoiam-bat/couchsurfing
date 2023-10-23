from pydantic import BaseModel, Field
from typing import List


class VerificationData(BaseModel):
    fullname: str = Field(min_length=2),
    passport_img: str
