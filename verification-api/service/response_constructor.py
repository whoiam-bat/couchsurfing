import model.verification_request as vreq
import model.verification_response as vres
import service.verification_service as vs


def construct_response(request: vreq.VerificationData):
    verified = vs.verify(request)

    return vres.ResponseData(status_code=200, status_message='User verified', is_verified=True) if verified \
        else vres.ResponseData(status_code=500, status_message='Verification error, bad quality of image.'
                                                               'Try to focus on MRZ at the bottom of document',
                               is_verified=False)
