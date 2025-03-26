import { useToastController } from 'bootstrap-vue-next';
import { getCurrentInstance } from 'vue';

export function useToast() {
  const instance = getCurrentInstance();
  if (!instance) {
    console.error('잘못된 Toast 요청입니다.');
    return {};
  }
  const { show } = useToastController();

  const showWarningToast = (message, duration = 2000) => {
    show({
      props: {
        title: `[경고] ${message}`,
        value: duration,
        variant: 'warning',
        progressProps: {
          variant: 'warning',
        },
        pos: 'bottom-end',
      },
    });
  };

  const showSuccessToast = (message, duration = 2000) => {
    show({
      props: {
        title: `[성공] ${message}`,
        value: duration,
        variant: 'success',
        progressProps: {
          variant: 'success',
        },
        pos: 'bottom-end',
      },
    });
  };

  const showErrorToast = (message, duration = 2000) => {
    show({
      props: {
        title: `[오류] ${message}`,
        value: duration,
        variant: 'danger',
        progressProps: {
          variant: 'danger',
        },
        pos: 'bottom-end',
      },
    });
  };

  const showInfoToast = (message, duration = 2000) => {
    show({
      props: {
        title: `[알림] ${message}`,
        value: duration,
        variant: 'info',
        progressProps: {
          variant: 'info',
        },
        pos: 'bottom-end',
      },
    });
  };

  return {
    showWarningToast,
    showSuccessToast,
    showErrorToast,
    showInfoToast,
  };
}
