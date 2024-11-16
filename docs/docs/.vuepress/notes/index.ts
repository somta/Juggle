import { defineNotesConfig } from 'vuepress-theme-plume'
import { suiteSchool } from './suite-school'
import {userGuide} from "./user-guide";
import {changeLog} from "./changelog";

export const zhNotes = defineNotesConfig({
    dir: 'notes',
    link: '/',
    notes: [
        userGuide,
        suiteSchool,
        changeLog,
    ],
})
