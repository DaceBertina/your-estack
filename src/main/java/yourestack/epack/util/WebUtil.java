package yourestack.epack.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.SneakyThrows;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Objects;


@Component
public class WebUtil {

    public static final String MSG_SUCCESS = "MSG_SUCCESS";
    public static final String MSG_INFO = "MSG_INFO";
    public static final String MSG_ERROR = "MSG_ERROR";
    private static MessageSource messageSource;
    private static LocaleResolver localeResolver;

    public WebUtil(final MessageSource messageSource, final LocaleResolver localeResolver) {
        WebUtil.messageSource = messageSource;
        WebUtil.localeResolver = localeResolver;
    }

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static String getMessage(final String code, final Object... args) {
        return messageSource.getMessage(code, args, code, localeResolver.resolveLocale(getRequest()));
    }

    @SneakyThrows
    public static boolean isRequiredField(final Object dto, final String fieldName) {
        return dto.getClass().getDeclaredField(fieldName).getAnnotation(NotNull.class) != null;
    }

}
