from pydantic import BaseModel


class ResponseData(BaseModel):
    status_code: int
    status_message: str
    is_verified: bool

