import i18n from "i18next";
import Backend from "i18next-http-backend"
import { initReactI18next } from 'react-i18next';
import translationEn from "./locales/en/translation.json";
import translationVi from "./locales/vi/translation.json";

const resources = {
    en :{
        translation : translationEn
    },
    vi :{
        translation : translationVi
    }
}

i18n.use(Backend)
.use(initReactI18next)
.init({
    resources,
    fallbackLng : 'vi',
    debug: true,
    interpolation : {
        escapeValue: false
    }
});
// i18n.use(Backend)
//     .use(initReactI18next)
//     .init({
//         resources,
//         fallbackLng: 'vi',
//         debug: true,
//         interpolation: {
//             escapeValue: false // not needed for react as it escapes by default
//         }
//     });
export default i18n;