export interface JSONResponse {
  code: string | 'ok' | 'ko' | 'error';
  content: string;
  message: string;
}
